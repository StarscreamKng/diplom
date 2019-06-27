package org.itstep.domain;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.itstep.domain.entity.*;
import org.itstep.service.BlogService;
import org.itstep.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;

@Component
@Scope(value = "singleton")
@Slf4j
public class InitDatabase {

    private BlogService blogService;
    private SecurityService securityService;
    private static boolean isInitialized;

    @Autowired
    public InitDatabase(BlogService blogService, SecurityService securityService) {
        this.blogService = blogService;
        this.securityService = securityService;
    }



    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if (!isInitialized) {
            log.info("Init database: " + blogService);

            log.info("Create admin");
            securityService.createAdmin(new User("admin", securityService.encode("admin")));
            securityService.createAdmin(new User("johnfisher", securityService.encode("johnfisher")));

            securityService.createAuthor(new User("john", securityService.encode("john")));
            securityService.createAuthor(new User("mary", securityService.encode("mary")));

            log.info("Init tags");
            val tag1 = blogService.saveTag(new Tag(null, "design", null));
            val tag2 = blogService.saveTag(new Tag(null, "fashion", null));
            val tag3 = blogService.saveTag(new Tag(null, "travel", null));
            val tag4 = blogService.saveTag(new Tag(null, "music", null));
            val tag5 = blogService.saveTag(new Tag(null, "party", null));
            val tag6 = blogService.saveTag(new Tag(null, "video", null));
            val tag7 = blogService.saveTag(new Tag(null, "photography", null));
            val tag8 = blogService.saveTag(new Tag(null, "adventure", null));

            log.info("Init category");
            val cat1 = blogService.saveCategory(new Category(null, "nature", null));
            val cat2 = blogService.saveCategory(new Category(null, "industrial", null));
            val cat3 = blogService.saveCategory(new Category(null, "people", null));
            val cat4 = blogService.saveCategory(new Category(null, "places", null));

            log.info("Init author");
            val author1 = blogService.saveAuthor(new Author("James", "Smith",
                    "jamessmith@gmail.com", "jamessmith",Status.ACTIVE,
                    "Curabitur venenatis efficitur lorem sed tempor. Integer aliquet tempor cursus. Nullam\n" +
                            " vestibulum convallis risus vel condimentum. Nullam auctor lorem in libero luctus, vel\n" +
                            " volutpat quam tincidunt. Nullam vestibulum convallis risus vel condimentum. Nullam auctor\n" +
                            " lorem in libero.",
                    "/img/bg-img/b6.jpg"));

            val author2 = blogService.saveAuthor(new Author( "Jake", "Fisher",
                    "jakefisher@gmail.com", "jakefisher" ,Status.ACTIVE,
                    "Curabitur venenatis efficitur lorem sed tempor. Integer aliquet tempor cursus. Nullam\n" +
                            " vestibulum convallis risus vel condimentum. Nullam auctor lorem in libero luctus, vel\n" +
                            " volutpat quam tincidunt. Nullam vestibulum convallis risus vel condimentum. Nullam auctor\n" +
                            " lorem in libero.",
                    "/img/bg-img/b7.jpg"));

            log.info("Init post");
            val post1 = blogService.savePost(new Post(null, author1, null, "Party people in the house",
                    "Curabitur venenatis efficitur lorem sed tempor. Integer aliquet tempor cursus. Nullam vestibulum " +
                            "convallis risus vel condimentum. Nullam auctor lorem in libero luctus, vel volutpat quam tincidunt.",
                    "3.jpg", cat1, Arrays.asList(tag1, tag2, tag3), false, null));

            for (int i = 4; i < 8; i++) {
                blogService.savePost(new Post(null, author1, LocalDateTime.now().minusDays(2), "We love colors in 2018",
                        "Curabitur venenatis efficitur lorem sed tempor. Integer aliquet tempor cursus. Nullam vestibulum " +
                                "convallis risus vel condimentum. Nullam auctor lorem in libero luctus, vel volutpat quam tincidunt.",
                        "4.jpg", cat2,
                        Collections.singletonList(tag1), false, null));


                blogService.savePost(new Post(null, author1, null, "10 Tips to organize the perfect party", "Curabitur venenatis " +
                        "efficitur lorem sed tempor. Integer aliquet tempor cursus. Nullam vestibulum convallis risus vel " +
                        "condimentum. Nullam auctor lorem in libero luctus, vel volutpat quam tincidunt.", "7.jpg", cat1,
                        Arrays.asList(tag1, tag3, tag4), true, null));

                blogService.savePost(new Post(null, author2, LocalDateTime.now().minusDays(2), "Party people in the house", "Curabitur venenatis efficitur " +
                        "lorem sed tempor. Integer aliquet tempor cursus. Nullam vestibulum convallis risus vel condimentum. " +
                        "Nullam auctor lorem in libero luctus, vel volutpat quam tincidunt.", "5.jpg", cat3,
                        Arrays.asList(tag3, tag4), false, null));

                blogService.savePost(new Post(null, author2, null, "We love colors in 2018", "Curabitur venenatis efficitur lorem sed " +
                        "tempor. Integer aliquet tempor cursus. Nullam vestibulum convallis risus vel condimentum. Nullam auctor " +
                        "lorem in libero luctus, vel volutpat quam tincidunt.", "6.jpg", cat4,
                        Collections.singletonList(tag2), false, null));
            }

            log.info("Init comments");
            blogService.saveComment(new Comment(null, "William James", "williamjames@gmail.com",
                    null, null, "Efficitur lorem sed " +
                    "tempor. Integer aliquet tempor cursus. Nullam vestibulum convallis risus vel condimentum. Nullam auctor " +
                    "lorem in libero luctus, vel volutpat quam tincidunt.", post1));


            blogService.saveComment(new Comment(null, "William James", "williamjames@gmail.com", null, null, "Efficitur lorem sed " +
                    "tempor. Integer aliquet tempor cursus. Nullam vestibulum convallis risus vel condimentum. Nullam auctor " +
                    "lorem in libero luctus, vel volutpat quam tincidunt.", post1));

            blogService.subscribe(new Subscriber("sentinel.v97@gmail.com"));
            blogService.subscribe(new Subscriber("shaptala@itstep.org"));
        }
        isInitialized = true;
    }
}
