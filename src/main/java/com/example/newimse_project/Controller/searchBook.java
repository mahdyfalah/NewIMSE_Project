package com.example.newimse_project.Controller;

import com.example.newimse_project.Model.*;
import com.example.newimse_project.Repository.*;
import com.example.newimse_project.filteringModel.filteringBook;
import com.example.newimse_project.filteringModel.filteringCategory;
import com.example.newimse_project.filteringModel.filteringChapters;
import com.example.newimse_project.filteringModel.filteringPublisher;
import lombok.extern.java.Log;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class searchBook {


    @Autowired
    private bookRepository book;
    @Autowired
    private userRepository userRepository;
    @Autowired
    private book_user_commentRepository repository_comment;
    @Autowired
    private book_user_readRepository book_user_readRepository;

    @Autowired
    chapterRepository chapterrepository;

    @Autowired
    private reportRepository firstReport;

    List<filteringBook> bookList = new ArrayList<>();
    List<filteringPublisher> publishers = new ArrayList<>();
    List<filteringCategory> categories = new ArrayList<>();
    List<filteringChapters> chapters = new ArrayList<>();

    String Link = "";
    String showMsgComment = "";

    Book book_store = new Book();
    User user_store = new User();

    Long bookID;

    filteringBook filteringbook;


    @GetMapping("/showBookForm/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id, Model model) {
        user_store = userRepository.getById(id);

        return "showBookForm";
    }

    @GetMapping("/searchBook")
    public String searchBook(@Param("keyword") String keyword, Model model) {

        List<Book> listbook = book.findAll();
        List<Chapters> listchap = chapterrepository.findAll();

        filteringPublisher publish;
        filteringCategory categoryfilter;

        categories.clear();
        bookList.clear();
        publishers.clear();
        chapters.clear();

         if(keyword.length()==0)
        {
            model.addAttribute("Data", "NoData");

        }
        else {  for(int i=0;i<listbook.size();i++)
        {
            if (keyword.trim().toLowerCase().equalsIgnoreCase(listbook.get(i).getName()))
            {
                bookID=listbook.get(i).getBookID();
                book_store = listbook.get(i);
                filteringbook = new filteringBook();
                filteringbook.setName(listbook.get(i).getName());
                filteringbook.setLink(listbook.get(i).getLink());
                filteringbook.setId(bookID);

                for (int j =0 ; j< listbook.get(i).getBookPublishers().size();j++)
                {
                    publish = new filteringPublisher();

                    Long publisherID = listbook.get(i).getBookPublishers().get(j).getId().getPublisherId();
                    publish.setName(listbook.get(i).getBookPublishers().get(j).getPublisher().getPulisher_name());
                    publish.setDate((listbook.get(i).getBookPublishers().get(j).getPublishedDate()));
                    publishers.add(publish);
                }
                for(int k=0;k<listbook.get(i).getBook_ncategories().size();k++)
                {
                    categoryfilter = new filteringCategory();
                    categoryfilter.setCategoryName(listbook.get(i).getBook_ncategories().get(k).getCategory().getTitle());
                    categoryfilter.setDescription(listbook.get(i).getBook_ncategories().get(k).getCategory().getDescription());
                    categories.add(categoryfilter);
                }

                filteringbook.setPublisherName(publishers);
                filteringbook.setCategories(categories);


            }
        }
        if(listchap.size()>0)
        {
            for(int c=0;c<listchap.size();c++)
            {
                if (bookID == listchap.get(c).getBook().getBookID())
                {

                    filteringChapters ch = new filteringChapters(listchap.get(c).getTitle(),listchap.get(c).getChapterNO());
                    chapters.add(ch);
                }
            }
            filteringbook.setChaptersNumber(chapters.size());

        }else
        {
            filteringbook.setChaptersNumber(0);

        }
     }

        bookList.add(filteringbook);
        if(bookList.size()>0) {
            model.addAttribute("listBooks", bookList);
        }else
        {
            model.addAttribute("Data", "NoData");
        }

        return "showBookForm";
    }

    @GetMapping("/showReadBookForm/{id}")
    public String showReadBookForm(@PathVariable(value = "id") Long id) {
        List<Book> listbook = book.findAll();
        for(int i=0;i<listbook.size();i++) {

        if( id == listbook.get(i).getBookID())
        {
            Link = listbook.get(i).getLink().trim().toString();

        }else
        {

        }

       }

        return "redirect:/FinalshowReadBookForm";
    }

    @GetMapping("/FinalshowReadBookForm")
    public String showReadBookForm(Model model) {

        Book_User_Read read_user = new Book_User_Read(book_store,user_store,new Date());

        if(Link.equalsIgnoreCase(""))
        {  model.addAttribute("Data", "NoData"); }

        else{  book_user_readRepository.save(read_user);

            model.addAttribute("chapter", chapters);
            model.addAttribute("gtmUrl", Link);

        }

        if(showMsgComment.equalsIgnoreCase("save"))
        {
            model.addAttribute("comment", "comment");
        }else if(showMsgComment.equalsIgnoreCase("notsaved"))
        {
            model.addAttribute("comment", "notsaved");
        }
        return "showReadBookForm";
    }

    @GetMapping("/sendComment")
    public String sendComment(@Param("comment") String comment) {

        Book_User_Comment commentuser = new Book_User_Comment(book_store,user_store,new Date(),comment);
        repository_comment.save(commentuser);
        try {
            repository_comment.save(commentuser);
            showMsgComment= "save";
        } catch(JDBCException e) {
            showMsgComment="notsaved";
        }


        return "redirect:/FinalshowReadBookForm";
    }

    @GetMapping("/report")
    public String report(Model model) {
//        List<ConvertObject> finalResults = new ArrayList<ConvertObject>();
//
        List<Object> results = firstReport.reportfirst();

        for (int i = 0; i < results.size(); i++) {


        }
//        model.addAttribute("listBooks", finalResults);
        return "ListReportBook";
    }
}
