package com.example.newimse_project.Controller;

import com.example.newimse_project.Model.*;
import com.example.newimse_project.Model.mongo.mongoBook;
import com.example.newimse_project.Model.mongo.mongoCategory;
import com.example.newimse_project.Model.mongo.mongoPublisher;
import com.example.newimse_project.Model.mongo.mongoUser;
import com.example.newimse_project.Repository.Mongo.mongoCategoryRepository;
import com.example.newimse_project.Repository.Mongo.mongoPublisherRepository;
import com.example.newimse_project.Repository.Mongo.mongoUserRepository;
import com.example.newimse_project.Repository.Mongo.monogoBookRepository;
import com.example.newimse_project.Repository.bookRepository;
import com.example.newimse_project.Repository.chapterRepository;
import com.example.newimse_project.Repository.publisherRepository;
import com.example.newimse_project.Repository.userRepository;
import com.example.newimse_project.filteringModel.filterID;
import com.example.newimse_project.filteringModel.filteringChapters;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class userRegister {

    @Autowired
    private bookRepository book;

    @Autowired
    private userRepository userRepository;

    @Autowired
    private mongoUserRepository mongouserRepository;

    @Autowired
    private monogoBookRepository bookRepository;

    @Autowired
    private mongoPublisherRepository publisherRepository;

    @Autowired
    private mongoCategoryRepository categoryRepository;

    @Autowired
    chapterRepository chapterrepository;
 ;

    List<Book> listbook1= new ArrayList<>();

    List<Chapters> listchap= new ArrayList<>();

    List<filterID> listfilterID= new ArrayList<>();
    List<filterID> listfilterreadBookID= new ArrayList<>();
    List<filterID> listfiltercommentBookID= new ArrayList<>();

    @GetMapping("/chooseUser")
    public String user(Model model) {
        List<User> listUsers = userRepository.findAll();
        model.addAttribute("listUsers", listUsers);
        return "chooseUser";
    }

    @GetMapping("/gotomongo")
    public String mongo() {

        listchap.clear();

        List<User> mongolistUsers = new ArrayList<>();
        mongolistUsers.clear();
        mongolistUsers  = userRepository.findAll();

        listchap = chapterrepository.findAll();
        listfilterID.clear();
        listfilterreadBookID.clear();
        listfiltercommentBookID.clear();
        for(int l=0;l<mongolistUsers.size();l++)
        {
            mongoUser user = new mongoUser(mongolistUsers.get(l).getFullName(),mongolistUsers.get(l).getEmail(),
                    mongolistUsers.get(l).getPhonenumber(),mongolistUsers.get(l).getAdress());
            mongouserRepository.insert(user);

            filterID filter = new filterID();
            filter.Id = mongolistUsers.get(l).getUserID();
            filter.IDs=user.getId();
            listfilterID.add(filter);

        }

        listbook1.clear();
        listbook1 = book.findAll();

        for(int i=0;i<listbook1.size();i++) {
            mongoBook book = new mongoBook(
                    listbook1.get(i).getName(),
                    listbook1.get(i).getLink(),
                    listbook1.get(i).getWritersName()
            );

            bookRepository.insert(book);

            List<DBObject> dbObjectListpublisher = new ArrayList<DBObject>();
            List<DBObject> db_user = new ArrayList<DBObject>();
            List<DBObject> dbObjectListb = new ArrayList<DBObject>();
            List<DBObject> dbObjectcomments = new ArrayList<DBObject>();



             for(int p=0;p<listbook1.get(i).getBookPublishers().size();p++)
            {
                mongoPublisher publisher = new mongoPublisher(
                        listbook1.get(i).getBookPublishers().get(p).getPublisher().getPulisher_name(),
                        listbook1.get(i).getBookPublishers().get(p).getPublisher().getEmail()
			);

            List<DBObject> dbObjectList = new ArrayList<DBObject>();
			DBObject dbo = new BasicDBObject();
			dbo.put("date", "" +listbook1.get(i).getBookPublishers().get(p).getPublishedDate());
			dbo.put("book_id", new ObjectId(book.getId()));
			dbo.put("book_name", listbook1.get(i).getName());
			dbObjectList.add(dbo);
			publisher.setBooks(dbObjectList);
        	publisherRepository.save(publisher);

                DBObject dbopublisher = new BasicDBObject();
                dbopublisher.put("date", "" + listbook1.get(i).getBookPublishers().get(p).getPublishedDate());
                dbopublisher.put("publisher_id", new ObjectId(publisher.getId()));
                dbopublisher.put("publisher_name",  listbook1.get(i).getBookPublishers().get(p).getPublisher().getPulisher_name());
                dbObjectListpublisher.add(dbopublisher);
                book.setPublishers(dbObjectListpublisher);

            }
            filterID fil = new filterID();
            fil.Id =  listbook1.get(i).getBookID();
            fil.IDs=book.getId();
            listfilterreadBookID.add(fil);
            for(int u=0;u<listbook1.get(i).getBook_user_reads().size();u++)
            {
                for(int l=0;l<listfilterID.size();l++)
                {
                    if(listfilterID.get(l).Id==listbook1.get(i).getBook_user_reads().get(u).getId().getUserId())
                    {

                        DBObject dbouseritem = new BasicDBObject();
                        dbouseritem.put("date", "" + listbook1.get(i).getBook_user_reads().get(u).getReadDate());
                        dbouseritem.put("user_id", new ObjectId(listfilterID.get(l).IDs));
                        db_user.add(dbouseritem);
                        book.setUserList(db_user);

                    }
                }
            }
            for(int t=0;t<listbook1.get(i).getBook_user_comments().size();t++)
            {
                for(int l=0;l<listfilterID.size();l++)
                {
                    if(listfilterID.get(l).Id==listbook1.get(i).getBook_user_comments().get(t).getId().getUserId())
                    {

                        DBObject dbcommentitem = new BasicDBObject();
                        dbcommentitem.put("date", "" + listbook1.get(i).getBook_user_comments().get(t).getCommentDate());
                        dbcommentitem.put("user_id", new ObjectId(listfilterID.get(l).IDs));
                        dbcommentitem.put("comment_text", listbook1.get(i).getBook_user_comments().get(t).getCommentText());
                        dbObjectcomments.add(dbcommentitem);
                        book.setComments(dbObjectcomments);

                    }
                }
            }
        	for(int c=0;c<listbook1.get(i).getBook_ncategories().size();c++)
            {
                mongoCategory caytegory = new mongoCategory( listbook1.get(i).getBook_ncategories().get(c).getCategory().getTitle(),
                        listbook1.get(i).getBook_ncategories().get(c).getCategory().getDescription());

                List<DBObject> dbObjectListc = new ArrayList<DBObject>();
                DBObject dboc = new BasicDBObject();
                dboc = new BasicDBObject();
                dboc.put("book_id", new ObjectId(book.getId()));
                dbObjectListc.add(dboc);
                caytegory.setBooks(dbObjectListc);
                categoryRepository.save(caytegory);

                DBObject dbob = new BasicDBObject();
                dbob.put("category_id", new ObjectId(caytegory.getId()));
                dbob.put("category_name", caytegory.getTitle());
                dbob.put("category_description", caytegory.getDescription());
                dbObjectListb.add(dbob);
                book.setCategories(dbObjectListb);
             }
            if(listchap.size()>0)
            {
                List<DBObject> dbObjectListch = new ArrayList<DBObject>();
                for(int c=0;c<listchap.size();c++)
                {
                    if (listbook1.get(i).getBookID() == listchap.get(c).getBook().getBookID())
                    {
                        DBObject dbo = new BasicDBObject();
                        dbo.put("Title", listchap.get(c).getTitle());
                        dbo.put("ChapterNo", listchap.get(c).getId().getChapterNO());
                        dbObjectListch.add(dbo);
                        book.setChapters(dbObjectListch);
                    }
                }

            }

            bookRepository.save(book);
        }

        List<Book_User_Read> book_user_reads = new ArrayList<>();
        List<Book_User_Comment> book_user_comments = new ArrayList<>();
        Optional<mongoUser> user_store = Optional.of(new mongoUser());

        for(int us=0;us<listfilterID.size();us++)
        {
            book_user_reads.clear();
            book_user_comments.clear();
            book_user_reads = userRepository.findById(listfilterID.get(us).Id).get().getBook_user_reads();
            book_user_comments = userRepository.findById(listfilterID.get(us).Id).get().getBook_user_comments();

            if(book_user_reads.size()>0)
            {
                user_store = mongouserRepository.findById(listfilterID.get(us).IDs);

                for(int read=0;read<book_user_reads.size();read++)
                {
                    for(int bb=0;bb<listfilterreadBookID.size();bb++)
                    {
                        if(listfilterreadBookID.get(bb).Id==book_user_reads.get(read).getBook().getBookID())
                        {
                            mongoUser mongoUser = user_store.get();
                            List<DBObject> bookList = mongoUser.getBookList();
                            DBObject dbo = new BasicDBObject();
                            dbo.put("date", "" + book_user_reads.get(read).getReadDate());
                            dbo.put("book_id", new ObjectId(listfilterreadBookID.get(bb).IDs));
                            bookList.add(dbo);
                            mongouserRepository.save(mongoUser);
                        }
                    }
                }

            }
            if(book_user_comments.size()>0)
            {
                user_store = mongouserRepository.findById(listfilterID.get(us).IDs);
                for(int com=0;com<book_user_comments.size();com++)
                {
                    for(int bb=0;bb<listfilterreadBookID.size();bb++)
                    {
                        if(listfilterreadBookID.get(bb).Id==book_user_comments.get(com).getBook().getBookID())
                        {
                            mongoUser mongoUser = user_store.get();
                            List<DBObject> commentList = mongoUser.getComments();
                            DBObject dbo = new BasicDBObject();
                            dbo.put("date", ""+ book_user_comments.get(com).getCommentDate());
                            dbo.put("comment_text", book_user_comments.get(com).getCommentText());
                            dbo.put("book_id", new ObjectId(listfilterreadBookID.get(bb).IDs));
                            commentList.add(dbo);
                            mongouserRepository.save(mongoUser);
                        }
                    }
                }
            }
        }



        return "redirect:/mongo/chooseUser";
    }

}
