/*******************************************************************************
 * This file is part of the EEG-database project
 *
 *   ==========================================
 *
 *   Copyright (C) 2013 by University of West Bohemia (http://www.zcu.cz/en/)
 *
 *  ***********************************************************************************************************************
 *
 *   Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 *   the License. You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 *   an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 *   specific language governing permissions and limitations under the License.
 *
 *  ***********************************************************************************************************************
 *
 *   ArticleDaoTest.java, 2013/10/02 00:01 Jakub Rinkes
 ******************************************************************************/
package cz.zcu.kiv.eegdatabase.data.dao;

import cz.zcu.kiv.eegdatabase.data.AbstractDataAccessTest;
import cz.zcu.kiv.eegdatabase.data.TestUtils;
import cz.zcu.kiv.eegdatabase.data.pojo.Article;
import cz.zcu.kiv.eegdatabase.data.pojo.Person;
import cz.zcu.kiv.eegdatabase.logic.Util;
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.testng.annotations.BeforeMethod;

import java.sql.Timestamp;
import java.util.Calendar;

import static org.testng.Assert.*;

/**
 * User: Tomas Pokryvka
 * Date: 20.4.13
 */
@TransactionConfiguration(defaultRollback = true)
public class ArticleDaoTest extends AbstractDataAccessTest {

    @Autowired
    private PersonDao personDao;

    @Autowired
    private ArticleDao articleDao;


    protected Article article;
    protected Person personReader;

    @BeforeMethod(groups = "unit")
    public void setUp() {
        personReader = TestUtils.createPersonForTesting("test@test.com", Util.ROLE_READER);
        personDao.create(personReader);


        article = new Article();

        article.setText("test-text");
        article.setTitle("test-title");
        article.setTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
        article.setPerson(personReader);
    }

    @Test(groups = "unit")
    public void testCreateArticleReader() {
        int count = articleDao.getCountRecords();
        articleDao.create(article);
        assertEquals(count + 1, articleDao.getCountRecords());
    }

    @Test(groups = "unit")
    public void testNotNullTitle() {
        article.setTitle(null);
        try {
            articleDao.create(article);
        } catch (Exception e) {
            assertTrue(e instanceof DataIntegrityViolationException);
            article.setTitle("test-title");

        } finally {
            Article tmp = articleDao.read(article.getArticleId());
            assertNull(tmp, "Article without title cannot be stored");

        }

    }

    @Test(groups = "unit")
    public void testEditArticle() {
        int count = articleDao.getCountRecords();
        int id = articleDao.create(article);
        assertEquals(count + 1, articleDao.getCountRecords());
        article.setText("new text");
        articleDao.update(article);
        assertEquals(count + 1, articleDao.getCountRecords());

        assertEquals("new text", articleDao.read(id).getText());
    }


//  @Test
//  @Transactional
//  public void testCteateArticleAdmin() {
//    article.setPerson(person_admin);
//    int count = articleDao.getCountRecords();
//    articleDao.create(article);
//    assertEquals(count + 1, articleDao.getCountRecords());
//  }
//
//
//  @Test
//  @Transactional
//  public void testGetAllArticleReader() {
//    int count = articleDao.getAllArticles().size();
//    articleDao.create(article);
//    assertEquals(count + 1, articleDao.getAllArticles().size());
//  }
//
//  @Test
//  @Transactional
//  public void testGetAllArticleAdmin() {
//    article.setPerson(person_admin);
//    int count = articleDao.getAllArticles().size();
//    articleDao.create(article);
//    assertEquals(count + 1, articleDao.getAllArticles().size());
//  }
//
//  @Test
//  @Transactional
//  public void testGetArticlesForUserReader() {
//    int count = articleDao.getArticlesForUser(personReader).size();
//    articleDao.create(article);
//    assertEquals(count + 1, articleDao.getArticlesForUser(personReader).size());
//  }
//
//  @Test
//  @Transactional
//  public void testGetArticlesForUserAdmin() {
//    article.setPerson(person_admin);
//    int count = articleDao.getArticlesForUser(person_admin).size();
//    articleDao.create(article);
//    assertEquals(count + 1, articleDao.getArticlesForUser(person_admin).size());
//  }
//
//  @Test
//  @Transactional
//  public void testGetArticleCountForPersonAdmin() {
//    article.setPerson(person_admin);
//    int count = articleDao.getArticleCountForPerson(person_admin);
//    articleDao.create(article);
//    assertEquals(count + 1, articleDao.getArticleCountForPerson(person_admin));
//  }
//
//  @Test
//  @Transactional
//  public void testGetArticleCountForPersonReader() {
//    int count = articleDao.getArticleCountForPerson(personReader);
//    articleDao.create(article);
//    assertEquals(count + 1, articleDao.getArticleCountForPerson(personReader));
//  }
//
//  @Test
//  @Transactional
//  public void testGetArticlesForList(){
//    int count = articleDao.getArticlesForList(person_admin, 0, 200).size();
//    article.setPerson(person_admin);
//    articleDao.create(article);
//    assertEquals(count + 1, articleDao.getArticlesForList(person_admin, 0, 200).size());
//
//    assertEquals(20, articleDao.getArticlesForList(person_admin, 0, 20).size());
//  }

}
