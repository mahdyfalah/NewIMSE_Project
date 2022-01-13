package com.example.newimse_project.Controller;

import com.example.newimse_project.Convert.ConvertObjectComment;
import com.example.newimse_project.Convert.ConvertObjectReading;
import com.example.newimse_project.Model.*;
import com.example.newimse_project.Repository.*;
import com.example.newimse_project.filteringModel.*;
import org.hibernate.JDBCException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
     book_user_readRepository readRepository;

    List<filteringBook> bookList = new ArrayList<>();
    List<filteringPublisher> publishers = new ArrayList<>();
    List<filteringCategory> categories = new ArrayList<>();
    List<filteringChapters> chapters = new ArrayList<>();
    List<filteringReadDate>  readDates= new ArrayList<>();


    String Link = "";
    String showMsgComment = "";

    Book book_store = new Book();
    User user_store = new User();

    Long bookID,publisher_id;

    filteringBook filteringbook;

    List<Book_User_Read>bookUserReads = new ArrayList<>();
    List<Book_User_Comment> book_user_comments = new ArrayList<>();

    List<Book> listbook1= new ArrayList<>();
    List<Book> listbook= new ArrayList<>();
    List<Chapters> listchap= new ArrayList<>();

    @GetMapping("/showBookForm/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") long id) {
        user_store = userRepository.getById(id);

        return "showBookForm";
    }

    @GetMapping("/searchBook")
    public String searchBook(@Param("keyword") String keyword, Model model) {

        listbook.clear();
        listchap.clear();

         listbook = book.findAll();

         listchap = chapterrepository.findAll();


        filteringPublisher publish;
        filteringCategory categoryfilter;

        categories.clear();
        bookList.clear();
        publishers.clear();
        chapters.clear();
        filteringbook = null;

         if(keyword.length()==0)
        {
            model.addAttribute("Data", "NoData");

        }
        else {
             for (int i = 0; i < listbook.size(); i++) {

                 if (keyword.trim().toLowerCase().equalsIgnoreCase(listbook.get(i).getName())) {
                     filteringbook = new filteringBook();
                     bookID = listbook.get(i).getBookID();
                     book_store = listbook.get(i);
                     filteringbook.setName(listbook.get(i).getName());
                     filteringbook.setLink(listbook.get(i).getLink());
                     filteringbook.setId(bookID);

                     for (int j = 0; j < listbook.get(i).getBookPublishers().size(); j++) {
                         publish = new filteringPublisher();

                         Long publisherID = listbook.get(i).getBookPublishers().get(j).getId().getPublisherId();
                         publish.setName(listbook.get(i).getBookPublishers().get(j).getPublisher().getPulisher_name());
                         publish.setDate((listbook.get(i).getBookPublishers().get(j).getPublishedDate()));
                         publish.setId((listbook.get(i).getBookPublishers().get(j).getPublisher().getUserID()));
                         publishers.add(publish);
                     }
                     for (int k = 0; k < listbook.get(i).getBook_ncategories().size(); k++) {
                         categoryfilter = new filteringCategory();
                         categoryfilter.setCategoryName(listbook.get(i).getBook_ncategories().get(k).getCategory().getTitle());
                         categoryfilter.setDescription(listbook.get(i).getBook_ncategories().get(k).getCategory().getDescription());
                         categories.add(categoryfilter);
                     }

                     filteringbook.setPublisherName(publishers);
                     filteringbook.setCategories(categories);


                 }
             }

             if (listchap.size() > 0) {
                 for (int c = 0; c < listchap.size(); c++) {
                     if (bookID == listchap.get(c).getBook().getBookID()) {

                         filteringChapters ch = new filteringChapters(listchap.get(c).getTitle(), listchap.get(c).getId().getChapterNO());
                         chapters.add(ch);

                     }
                 }
                 if(filteringbook!=null)
                 {
                     filteringbook.setChaptersNumber(chapters.size());
                 }

             } else {
                 if(filteringbook!=null) {
                     filteringbook.setChaptersNumber(0);
                 }
             }
             if(filteringbook!=null) {
                 bookList.add(filteringbook);
             }
         }

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

        listbook1.clear();
        listbook1 = book.findAll();

        for(int i=0;i<listbook1.size();i++) {

        if( id == listbook1.get(i).getBookID())
        {
            Link = listbook1.get(i).getLink().trim().toString();
            List<Object[]> resultsreport = firstReport.get_id_user_read(user_store.getUserID(),book_store.getBookID());
            Long read_id = Long.valueOf(resultsreport.size() + 1);
            Book_User_Read read_user = new Book_User_Read(book_store,user_store, get_date(),read_id);
            book_user_readRepository.save(read_user);

        }
        }

        return "redirect:/FinalshowReadBookForm";
    }

    @GetMapping("/FinalshowReadBookForm")
    public String showReadBookForm(Model model) {


        if(showMsgComment.equalsIgnoreCase("save"))
        {
            model.addAttribute("postcomment", "comment");
            showMsgComment = "";

        }else if(showMsgComment.equalsIgnoreCase("notsaved"))
        {
            model.addAttribute("postcomment", "notsaved");
            showMsgComment= "";
        }
        else
        {
            if(Link.equalsIgnoreCase(""))
            {
                model.addAttribute("Data", "NoData");
            }
            else{

                model.addAttribute("chapter", chapters);
                model.addAttribute("gtmUrl", Link);
            }

            model.addAttribute("postcomment", "");
        }


        return "showReadBookForm";
    }

    @GetMapping("/sendComment")
    public String sendComment(@Param("comment") String comment,RedirectAttributes redirectAttrs) {

        List<Object[]> resultsreportfirst_1 = firstReport.get_id_user_comment(user_store.getUserID(),book_store.getBookID());

        Long comment_id = Long.valueOf(resultsreportfirst_1.size() + 1);

        if(comment.equals(""))
        {

        }
        else
        {
            Book_User_Comment commentuser = new Book_User_Comment(book_store,user_store,get_date(),comment,comment_id);
            repository_comment.save(commentuser);
            try {
                repository_comment.save(commentuser);
                showMsgComment= "save";
            } catch(JDBCException e) {
                showMsgComment="notsaved";
            }

            redirectAttrs.addAttribute("comment","saved");

        }


        return "redirect:/FinalshowReadBookForm";
    }


    @GetMapping("/reportbook_read")
    public String reportbook_read(Model model) {
        readDates.clear();
        bookUserReads.clear();
        bookUserReads = readRepository.findAll();

        for(int d=0;d<bookUserReads.size();d++)
        {
            filteringReadDate date = new filteringReadDate();
            date.setGet_date(bookUserReads.get(d).getReadDate());
            readDates.add(date);
        }

        Set<LocalDate> Set = new HashSet<>();
        List<filteringReadDate> dateList = readDates.stream()
                .filter(e -> Set.add(e.getGet_date()))
                .collect(Collectors.toList());

        model.addAttribute("dateList", dateList);
        return "reportForm";
    }


    @GetMapping("/reportbook_comment")
    public String reportbook_comment(Model model) {

        readDates.clear();
        book_user_comments.clear();
        book_user_comments = repository_comment.findAll();

        for(int d=0;d<book_user_comments.size();d++)
        {
            filteringReadDate date = new filteringReadDate();
            date.setGet_date(book_user_comments.get(d).getCommentDate());
            readDates.add(date);
        }

        Set<LocalDate> Set = new HashSet<>();
        List<filteringReadDate> dateList = readDates.stream()
                .filter(e -> Set.add(e.getGet_date()))
                .collect(Collectors.toList());

        model.addAttribute("dateList", dateList);
        return "reportFormComment";
    }


    @GetMapping("/FilterCommentBookbyDate/{date}")
    public String FilterCommentBookbyDate(@PathVariable(value = "date") String date,Model model) {

        List<Object[]> report_comment = firstReport.report_comment(date);
        List<ConvertObjectComment> Results_1 = new ArrayList<ConvertObjectComment>();
         List<ConvertObjectComment> Results_2 = new ArrayList<ConvertObjectComment>();

        Results_1.clear();
        Results_2.clear();

        for (int i = 0; i < report_comment.size(); i++) {
            String username = (report_comment.get(i)[0] instanceof String) ? (String) report_comment.get(i)[0] : null;
            String bookname = (report_comment.get(i)[1] instanceof String) ? (String) report_comment.get(i)[1] : null;
            String categoryname = (report_comment.get(i)[2] instanceof String) ? (String) report_comment.get(i)[2] : null;
            String comment = (report_comment.get(i)[3] instanceof String) ? (String) report_comment.get(i)[3] : null;
            BigInteger comment_id = (report_comment.get(i)[4] instanceof BigInteger) ? (BigInteger) report_comment.get(i)[4] : null;
            BigInteger user_id = (report_comment.get(i)[5] instanceof BigInteger) ? (BigInteger) report_comment.get(i)[5] : null;
            BigInteger book_id = (report_comment.get(i)[6] instanceof BigInteger) ? (BigInteger) report_comment.get(i)[6] : null;


            ConvertObjectComment convertObjectcomment = new ConvertObjectComment(username, bookname, categoryname,comment,comment_id,user_id,book_id);
            Results_1.add(convertObjectcomment);
        }
        int j =0;
        for(int f = 0; f <Results_1.size(); f++)
        {
            j = f+1;
            if(j<Results_1.size())
            {
                if(Results_1.get(f).getUser_id() == Results_1.get(j).getUser_id() &&
                        Results_1.get(f).getBook_id() == (Results_1.get(j).getBook_id()) &&
                        (Results_1.get(f).getComment_id()==(Results_1.get(j).getComment_id())) )
                {
                    ConvertObjectComment convertObject = new ConvertObjectComment(Results_1.get(f).getUserName(),
                            Results_1.get(f).getBookName(),
                            Results_1.get(f).getCategory() + "," +
                                    Results_1.get(j).getCategory(),
                            Results_1.get(f).getComment(),
                            Results_1.get(f).getComment_id(),
                            Results_1.get(f).getUser_id(),Results_1.get(f).getBook_id());
                    Results_2.add(convertObject);
                    Results_1.remove(j);
                    Results_1.remove(f);
                    f = f-1;
                }else
                {
                    ConvertObjectComment convertObject = new ConvertObjectComment(Results_1.get(f).getUserName(),
                            Results_1.get(f).getBookName(),
                            Results_1.get(f).getCategory()  ,
                            Results_1.get(f).getComment()  ,
                            Results_1.get(f).getComment_id(),
                            Results_1.get(f).getUser_id(),
                            Results_1.get(f).getBook_id());
                    Results_2.add(convertObject);
                    Results_1.remove(f);
                    f = f-1;
                }
            }else
            {
                ConvertObjectComment convertObject = new ConvertObjectComment(Results_1.get(f).getUserName(),
                        Results_1.get(f).getBookName(),
                        Results_1.get(f).getCategory()  ,
                        Results_1.get(f).getComment()  ,
                        Results_1.get(f).getComment_id(),
                        Results_1.get(f).getUser_id(),
                        Results_1.get(f).getBook_id());
                Results_2.add(convertObject);

            }

        }


        model.addAttribute("report", Results_2);

        return "FilterCommentBookbyDate";
    }

    @GetMapping("/FilterReadBookbyDate/{date}")
     public String FilterReadBookbyDate(@PathVariable(value = "date") String date,Model model) {


        List<Object[]> resultsreport = firstReport.report_book(date);
        List<ConvertObjectReading> Results1 = new ArrayList<ConvertObjectReading>();
        List<ConvertObjectReading> Results2 = new ArrayList<ConvertObjectReading>();

        Results2.clear();
        Results1.clear();

        for (int i = 0; i < resultsreport.size(); i++) {
            String username = (resultsreport.get(i)[0] instanceof String) ? (String) resultsreport.get(i)[0] : null;
            String bookname = (resultsreport.get(i)[1] instanceof String) ? (String) resultsreport.get(i)[1] : null;
            String publishername = (resultsreport.get(i)[2] instanceof String) ? (String) resultsreport.get(i)[2] : null;
            BigInteger read_id = (resultsreport.get(i)[3] instanceof BigInteger) ? (BigInteger) resultsreport.get(i)[3] : null;
            BigInteger user_id = (resultsreport.get(i)[4] instanceof BigInteger) ? (BigInteger) resultsreport.get(i)[4] : null;
            BigInteger book_id = (resultsreport.get(i)[5] instanceof BigInteger) ? (BigInteger) resultsreport.get(i)[5] : null;

            ConvertObjectReading convertObjectReading = new ConvertObjectReading(username, bookname,
                    publishername, read_id,user_id,book_id);
            Results1.add(convertObjectReading);
        }
        int j =0;
        for(int f = 0; f <Results1.size(); f++)
        {
            j = f+1;
            if(j<Results1.size())
            {
                if(Results1.get(f).getUser_id() == Results1.get(j).getUser_id() &&
                        Results1.get(f).getBook_id() == (Results1.get(j).getBook_id()) &&  !Results1.get(f).getPublishername() .equalsIgnoreCase (Results1.get(j).getPublishername()) )
                {
                    ConvertObjectReading convertObjectReading = new ConvertObjectReading(Results1.get(f).getUserName(), Results1.get(f).getBookName(),
                            Results1.get(f).getPublishername() + "," +
                                    Results1.get(j).getPublishername(),
                            Results1.get(f).getRead_id(), Results1.get(f).getUser_id(),
                            Results1.get(f).getBook_id());
                    Results2.add(convertObjectReading);
                    Results1.remove(j);
                    Results1.remove(f);
                    f = f-1;
                }else
                {
                    ConvertObjectReading convertObjectReading = new ConvertObjectReading(Results1.get(f).getUserName(), Results1.get(f).getBookName(),
                            Results1.get(f).getPublishername() ,
                            Results1.get(f).getRead_id(), Results1.get(f).getUser_id(),
                            Results1.get(f).getBook_id());
                    Results2.add(convertObjectReading);
                    Results1.remove(f);
                    f = f-1;
                }
            }else
            {
                ConvertObjectReading convertObjectReading = new ConvertObjectReading(Results1.get(f).getUserName(),
                        Results1.get(f).getBookName(),
                        Results1.get(f).getPublishername()  ,
                        Results1.get(f).getRead_id(),
                        Results1.get(f).getUser_id(),
                        Results1.get(f).getBook_id());
                Results2.add(convertObjectReading);

            }

        }



        model.addAttribute("report", Results2);

        return "FilterReadBookbyDate";
    }

    public LocalDate get_date()
    {

        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        Date dateobj = new Date();

        DateTimeFormatter f = DateTimeFormatter.ofPattern( "dd/MM/yyyy" );
        LocalDate ld = LocalDate.parse( df.format(dateobj) , f );

        return ld;
    }
}
