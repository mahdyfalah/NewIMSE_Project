package com.example.newimse_project.Controller.mongo;

import com.example.newimse_project.Model.mongo.mongoBook;
import com.example.newimse_project.Model.mongo.mongoCommentReport;
import com.example.newimse_project.Model.mongo.mongoReadReport;
import com.example.newimse_project.Model.mongo.mongoUser;
import com.example.newimse_project.Repository.Mongo.monogoBookRepository;
import com.example.newimse_project.Repository.Mongo.mongoUserRepository;
import com.example.newimse_project.filteringModel.filteringChapters;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    @Autowired
    private mongoUserRepository mongoUserRepository;

    @Autowired
    private monogoBookRepository monogoBookRepository;

    Optional<mongoUser> user_store = Optional.of(new mongoUser());
    Optional<mongoBook> book_store = Optional.of(new mongoBook());

    String Link = "";
    String showMsgComment = "";

    List<filteringChapters> chapters = new ArrayList<>();

    @GetMapping("/mongo/chooseUser")
    public String user(Model model) {
        List<mongoUser> listMongoUsers = mongoUserRepository.findAll();
        model.addAttribute("listUsers", listMongoUsers);
        return "mongochooseUser";
    }

    @GetMapping("/mongo/showBookForm/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") String id) {
        user_store = mongoUserRepository.findById(id);
        return "mongoshowBookForm";
    }

    @GetMapping("/mongo/searchBook")
    public String searchBook(@Param("keyword") String keyword, Model model) {
        List<mongoBook> mongoBookList = monogoBookRepository.findBookByName(keyword.trim().toLowerCase());

        if(mongoBookList.size()>0)
        {
            model.addAttribute("bookList", mongoBookList);
        }else
        {
            model.addAttribute("Data", "NoData");
        }

        return "mongoshowBookForm";
    }

    @GetMapping("/mongo/showReadBookForm/{id}")
    public String showReadBookForm(@PathVariable(value = "id") String id){
        mongoUser mongoUser = user_store.get();
        mongoBook mongoBook = monogoBookRepository.findById(id).get();
        chapters.clear();
        Link = mongoBook.getLink();

        for(int cc=0;cc<mongoBook.getChapters().size();cc++)
        {
            filteringChapters ch = new filteringChapters(mongoBook.getChapters().get(cc).get("Title").toString(),
                   Long.valueOf(mongoBook.getChapters().get(cc).get("ChapterNo").toString()));
             chapters.add(ch);
        }


//        add read to user
        List<DBObject> bookList = mongoUser.getBookList();

        DBObject dbo = new BasicDBObject();
        dbo.put("date", "" + java.time.LocalDate.now());
        dbo.put("book_id", new ObjectId(id));


        bookList.add(dbo);
        mongoUserRepository.save(mongoUser);

//        add read to book
        List<DBObject> userList = mongoBook.getUserList();

        dbo = new BasicDBObject();
        dbo.put("date", "" + java.time.LocalDate.now());
        dbo.put("user_id", new ObjectId(mongoUser.getId()));

        userList.add(dbo);
        monogoBookRepository.save(mongoBook);

        book_store = monogoBookRepository.findById(id);
        return "redirect:/mongo/FinalshowReadBookForm";
    }

    @GetMapping("/mongo/FinalshowReadBookForm")
    public String showReadBookForm(Model model){
        if(showMsgComment.equalsIgnoreCase("save"))
        {
            model.addAttribute("postcomment", "comment");
            showMsgComment = "";
        }else if(showMsgComment.equalsIgnoreCase("notsaved"))
        {
            model.addAttribute("postcomment", "notsaved");
            showMsgComment= "";
        }
        else {
            if(Link.equalsIgnoreCase(""))
            {
                model.addAttribute("Data", "NoData");
            }
            else {
                model.addAttribute("chapter", chapters);
                model.addAttribute("gtmUrl", Link);
            }
        }

        return "mongoshowReadBookForm";
    }

    @GetMapping("/mongo/reportbook_read")
    public String readReport(Model model){
        List<String> dateList = monogoBookRepository.getAllUniqueReadDates();
        model.addAttribute("dateList", dateList);
        return "mongoreportRead";
    }

    @GetMapping("/mongo/showReadReport/{date}")
    public String showReadReport(@PathVariable(value = "date") String date, Model model){
        List<mongoReadReport> report = monogoBookRepository.readReport(date);
        model.addAttribute("report", report);
        return "mongoreadReport";
    }

    @GetMapping("/mongo/sendComment")
    public String sendComment(@Param("comment") String comment, RedirectAttributes redirectAttrs) {
        mongoUser mongoUser = user_store.get();
        mongoBook mongoBook = book_store.get();

//        add comment to book
        if(comment.equals(""))
        {}
        else
        {
            List<DBObject> bookComments = mongoBook.getComments();

            DBObject dbo = new BasicDBObject();

            dbo.put("date", ""+ java.time.LocalDate.now());
            dbo.put("comment_text", comment);
            dbo.put("user_id", new ObjectId(mongoUser.getId()));

            bookComments.add(dbo);
            monogoBookRepository.save(mongoBook);

//        add comment to user

            List<DBObject> userComments = mongoUser.getComments();
            dbo = new BasicDBObject();

            dbo.put("date", ""+ java.time.LocalDate.now());
            dbo.put("comment_text", comment);
            dbo.put("book_id", new ObjectId(mongoBook.getId()));

            userComments.add(dbo);

            try {
                mongoUserRepository.save(mongoUser);
                showMsgComment= "save";
            } catch(JDBCException e) {
                showMsgComment="notsaved";
            }

            redirectAttrs.addAttribute("comment","saved");

        }


        return "redirect:/mongo/FinalshowReadBookForm";
    }

    @GetMapping("/mongo/reportbook_comment")
    public String commentReport(Model model){
        List<String> dateList = monogoBookRepository.getAllUniqueCommentDates();
        model.addAttribute("dateList", dateList);
        return "mongoreportComment";
    }

    @GetMapping("/mongo/showCommentReport/{date}")
    public String showCommentReport(@PathVariable(value = "date") String date, Model model){
        List<mongoCommentReport> report = monogoBookRepository.commentReport(date);
        model.addAttribute("report", report);
        return "mongocommentReport";
    }

}
