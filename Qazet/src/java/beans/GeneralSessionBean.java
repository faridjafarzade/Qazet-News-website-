/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import bean.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;
import javax.naming.spi.DirStateFactory;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import pojoes.Admin;
import pojoes.Advertisement;
import pojoes.Article;
import pojoes.Image;
import pojoes.Link;
import pojoes.News;
import pojoes.Poll;
import pojoes.Slide;
import pojoes.Topic;
import pojoes.Video;
import pojoes.WebSite;

public class GeneralSessionBean implements Serializable {

    GeneralSessionBean() {
        emf = Persistence.createEntityManagerFactory("Qazet");
    }
    public EntityManagerFactory emf = Persistence.createEntityManagerFactory("Qazet");
    private List<Topic> topics = new ArrayList<Topic>();
    private List<Admin> adminss = new ArrayList<Admin>();
    private List<Link> links = new ArrayList<Link>();
    private List<Video> videos = new ArrayList<Video>();
    private List<Poll> polles = new ArrayList<Poll>();
    private List<Slide> slides = new ArrayList<Slide>();
    private List<WebSite> websites = new ArrayList<WebSite>();
    private List<News> news = new ArrayList<News>();
    private List<Advertisement> advertisements = new ArrayList<Advertisement>();

    EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void creatTopic(Topic topic) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(topic);
            em.getTransaction().commit();
        } catch (Exception e) {
        } finally {
            em.close();
        }
    }

    public void creatWebSite(WebSite webSite) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(webSite);
            em.getTransaction().commit();
        } catch (Exception e) {
        } finally {
            em.close();
        }
    }

    public Article creatArticle(Article article) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(article);
            em.getTransaction().commit();
            System.out.println(article.getId());
            return article;
        } catch (Exception e) {
            return null;
        } finally {

            em.close();

        }
    }

    public void creatAdvertisement(Advertisement adv) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(adv);
            em.getTransaction().commit();

        } catch (Exception e) {
        } finally {

            em.close();

        }
    }

    public Poll creatPoll(Poll poll) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(poll);
            em.getTransaction().commit();
            return poll;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();

        }
    }

    public void creatNews(News news) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(news);
            em.getTransaction().commit();
        } catch (Exception e) {
        } finally {
            em.close();

        }
    }

    public Slide creatSlide(Slide slide) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(slide);
            em.getTransaction().commit();
            return slide;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();

        }
    }

    public void creatAdmin(Admin admin) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(admin);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public Video creatVideo(Video video) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(video);
            em.getTransaction().commit();
            return video;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();

        }
    }

    public Link creatLink(Link link) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(link);
            em.getTransaction().commit();
            return link;
        } catch (Exception e) {
            return null;
        } finally {
            em.close();

        }
    }

    public void creatImage(Image image) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(image);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Admin> findAdminEntities() {
        return findAdminEntities(true, -1, -1);
    }

    public List<Admin> findAdminEntities(int maxResults, int firstResult) {
        return findAdminEntities(false, maxResults, firstResult);
    }

    private List<Admin> findAdminEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Admin as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<News> findNewsEntitiesWithTopicName(String topicName) {
        return findNewsEntitiesWithTopicName(true, -1, -1, topicName);
    }

    public List<News> findNewsEntitiesWithTopicName(int maxResults, int firstResult, String topicName) {
        return findNewsEntitiesWithTopicName(false, maxResults, firstResult, topicName);
    }

    private List<News> findNewsEntitiesWithTopicName(boolean all, int maxResults, int firstResult, String topicName) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from News as o where o.topic.name =:parent");
            q.setParameter("parent", topicName);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            System.out.println(q.getResultList().size() + "");
            return q.getResultList();
        } finally {
            em.close();
        }

    }

    public List<News> findNewsEntities() {
        return findNewsEntities(true, -1, -1);
    }

    public List<News> findNewsEntities(int maxResults, int firstResult) {
        return findNewsEntities(false, maxResults, firstResult);
    }

    private List<News> findNewsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from News as o ");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<News> findArticleNewsEntities() {
        return findArticleNewsEntities(true, -1, -1);
    }

    public List<News> findArticleNewsEntities(int maxResults, int firstResult) {
        return findArticleNewsEntities(false, maxResults, firstResult);
    }

    private List<News> findArticleNewsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from News as o where o.article!= :parent");
            q.setParameter("parent", null);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    private List<News> findArticleNewsEntitiesWithName(boolean all, int maxResults, int firstResult, String name) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from News as o where o.article.name= :parent");
            q.setParameter("parent", name);

            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<News> findLinkNewsEntities() {
        return findLinkNewsEntities(true, -1, -1);
    }

    public List<News> findLinkNewsEntities(int maxResults, int firstResult) {
        return findLinkNewsEntities(false, maxResults, firstResult);
    }

    private List<News> findLinkNewsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from News as o where o.link!= :parent");
            q.setParameter("parent", null);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<News> findVideoNewsEntities() {
        return findVideoNewsEntities(true, -1, -1);
    }

    public List<News> findVideoNewsEntities(int maxResults, int firstResult) {
        return findVideoNewsEntities(false, maxResults, firstResult);
    }

    private List<News> findVideoNewsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from News as o where o.video=:parent");
            q.setParameter("parent", null);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<News> findSlideNewsEntities() {
        return findSlideNewsEntities(true, -1, -1);
    }

    public List<News> findSlideNewsEntities(int maxResults, int firstResult) {
        return findSlideNewsEntities(false, maxResults, firstResult);
    }

    private List<News> findSlideNewsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from News as o where o.slide=:parent");
            q.setParameter("parent", null);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<News> findPollNewsEntities() {
        return findPollNewsEntities(true, -1, -1);
    }

    public List<News> findPollNewsEntities(int maxResults, int firstResult) {
        return findPollNewsEntities(false, maxResults, firstResult);
    }

    private List<News> findPollNewsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from News as o where o.poll=:parent");
            q.setParameter("parent", null);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<News> findArticleNewsEntitiesWithTopicName(String topic) {
        return findArticleNewsEntitiesWithTopicName(true, -1, -1, topic);
    }

    public List<News> findArticleNewsEntitiesWithTopicName(int maxResults, int firstResult, String topic) {
        return findArticleNewsEntitiesWithTopicName(false, maxResults, firstResult, topic);
    }

    private List<News> findArticleNewsEntitiesWithTopicName(boolean all, int maxResults, int firstResult, String topic) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from News as o where o.article!= null and o.topic.name=:parent");
            q.setParameter("parent", topic);

            // q.setParameter("no", null);

            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<News> findLinkNewsEntitiesWithTopicName(String topic) {
        return findLinkNewsEntitiesWithTopicName(true, -1, -1, topic);
    }

    public List<News> findLinkNewsEntitiesWithTopicName(int maxResults, int firstResult, String topic) {
        return findLinkNewsEntitiesWithTopicName(false, maxResults, firstResult, topic);
    }

    private List<News> findLinkNewsEntitiesWithTopicName(boolean all, int maxResults, int firstResult, String topic) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from News as o where o.link!=null and o.topic.name=:parent");
            q.setParameter("parent", topic);
            // q.setParameter("no", null);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<News> findVideoNewsEntitiesWithTopicName(String topic) {
        return findVideoNewsEntitiesWithTopicName(true, -1, -1, topic);
    }

    public List<News> findVideoNewsEntitiesWithTopicName(int maxResults, int firstResult, String topic) {
        return findVideoNewsEntitiesWithTopicName(false, maxResults, firstResult, topic);
    }

    private List<News> findVideoNewsEntitiesWithTopicName(boolean all, int maxResults, int firstResult, String topic) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from News as o where o.video!=null and o.topic.name=:parent");
            q.setParameter("parent", topic);
            //q.setParameter("no", null);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<News> findSlideNewsEntitiesWithTopicName(String topic) {
        return findSlideNewsEntitiesWithTopicName(true, -1, -1, topic);
    }

    public List<News> findSlideNewsEntitiesWithTopicName(int maxResults, int firstResult, String topic) {
        return findSlideNewsEntitiesWithTopicName(false, maxResults, firstResult, topic);
    }

    private List<News> findSlideNewsEntitiesWithTopicName(boolean all, int maxResults, int firstResult, String topic) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from News as o where o.slide!=null and o.topic.name=:parent");
            q.setParameter("parent", topic);
            // q.setParameter("no", null);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<News> findPollNewsEntitiesWithTopicName(String topic) {
        return findPollNewsEntitiesWithTopicName(true, -1, -1, topic);
    }

    public List<News> findPollNewsEntitiesWithTopicName(int maxResults, int firstResult, String topic) {
        return findPollNewsEntitiesWithTopicName(false, maxResults, firstResult, topic);
    }

    private List<News> findPollNewsEntitiesWithTopicName(boolean all, int maxResults, int firstResult, String topic) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from News as o where o.poll!=null and o.topic.name=:parent");
            q.setParameter("parent", topic);
            // q.setParameter("no", null);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<News> findLastArticleNewsEntities() {
        return findLastArticleNewsEntities(true, -1, -1);
    }

    public List<News> findLastArticleNewsEntities(int maxResults, int firstResult) {
        return findLastArticleNewsEntities(false, maxResults, firstResult);
    }

    private List<News> findLastArticleNewsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from News as o where o.article!= null ORDER BY o.date DESC");




            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<News> findLastArticleNewsEntitiesWithTopicName(String topic) {
        return findLastArticleNewsEntitiesWithTopicName(true, -1, -1, topic);
    }

    public List<News> findLastArticleNewsEntitiesWithTopicName(int maxResults, int firstResult, String topic) {
        return findLastArticleNewsEntitiesWithTopicName(false, maxResults, firstResult, topic);
    }

    private List<News> findLastArticleNewsEntitiesWithTopicName(boolean all, int maxResults, int firstResult, String topic) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from News as o where o.article!= null and o.topic.name=:parent ORDER BY o.date DESC");
            q.setParameter("parent", topic);



            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<News> findLastLinkNewsEntitiesWithTopicName(String topic) {
        return findLastLinkNewsEntitiesWithTopicName(true, -1, -1, topic);
    }

    public List<News> findLastLinkNewsEntitiesWithTopicName(int maxResults, int firstResult, String topic) {
        return findLastLinkNewsEntitiesWithTopicName(false, maxResults, firstResult, topic);
    }

    private List<News> findLastLinkNewsEntitiesWithTopicName(boolean all, int maxResults, int firstResult, String topic) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from News as o where o.link!=null and o.topic.name=:parent  ORDER BY o.date DESC");
            q.setParameter("parent", topic);
            // q.setParameter("no", null);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<News> findLastLinkNewsEntities() {
        return findLastLinkNewsEntities(true, -1, -1);
    }

    public List<News> findLastLinkNewsEntities(int maxResults, int firstResult) {
        return findLastLinkNewsEntities(false, maxResults, firstResult);
    }

    private List<News> findLastLinkNewsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from News as o where o.link!=null ORDER BY o.date DESC");
            // q.setParameter("no", null);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<News> findLastVideoNewsEntitiesWithTopicName(String topic) {
        return findLastVideoNewsEntitiesWithTopicName(true, -1, -1, topic);
    }

    public List<News> findLastVideoNewsEntitiesWithTopicName(int maxResults, int firstResult, String topic) {
        return findLastVideoNewsEntitiesWithTopicName(false, maxResults, firstResult, topic);
    }

    private List<News> findLastVideoNewsEntitiesWithTopicName(boolean all, int maxResults, int firstResult, String topic) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from News as o where o.video!=null and o.topic.name=:parent ORDER BY o.date DESC");
            q.setParameter("parent", topic);
            //q.setParameter("no", null);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<News> findLastVideoNewsEntities() {
        return findLastVideoNewsEntities(true, -1, -1);
    }

    public List<News> findLastVideoNewsEntities(int maxResults, int firstResult) {
        return findLastVideoNewsEntities(false, maxResults, firstResult);
    }

    private List<News> findLastVideoNewsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from News as o where o.video!=null ORDER BY o.date DESC");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<News> findLastSlideNewsEntitiesWithTopicName(String topic) {
        return findLastSlideNewsEntitiesWithTopicName(true, -1, -1, topic);
    }

    public List<News> findLastSlideNewsEntitiesWithTopicName(int maxResults, int firstResult, String topic) {
        return findLastSlideNewsEntitiesWithTopicName(false, maxResults, firstResult, topic);
    }

    private List<News> findLastSlideNewsEntitiesWithTopicName(boolean all, int maxResults, int firstResult, String topic) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from News as o where o.slide!=null and o.topic.name=:parent ORDER BY o.date DESC");
            q.setParameter("parent", topic);
            // q.setParameter("no", null);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<News> findLastSlideNewsEntities() {
        return findLastSlideNewsEntities(true, -1, -1);
    }

    public List<News> findLastSlideNewsEntities(int maxResults, int firstResult) {
        return findLastSlideNewsEntities(false, maxResults, firstResult);
    }

    private List<News> findLastSlideNewsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from News as o where o.slide!=null ORDER BY o.date DESC");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<News> findLastPollNewsEntitiesWithTopicName(String topic) {
        return findLastPollNewsEntitiesWithTopicName(true, -1, -1, topic);
    }

    public List<News> findLastPollNewsEntitiesWithTopicName(int maxResults, int firstResult, String topic) {
        return findLastPollNewsEntitiesWithTopicName(false, maxResults, firstResult, topic);
    }

    private List<News> findLastPollNewsEntitiesWithTopicName(boolean all, int maxResults, int firstResult, String topic) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from News as o where o.poll!=null and o.topic.name=:parent ORDER BY o.date DESC");
            q.setParameter("parent", topic);
            // q.setParameter("no", null);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<News> findLastPollNewsEntities() {
        return findLastPollNewsEntities(true, -1, -1);
    }

    public List<News> findLastPollNewsEntities(int maxResults, int firstResult) {
        return findLastPollNewsEntities(false, maxResults, firstResult);
    }

    private List<News> findLastPollNewsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from News as o where o.poll!=null ORDER BY o.date DESC");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Slide> findSlideEntities() {
        return findSlideEntities(true, -1, -1);
    }

    public List<Slide> findSlideEntities(int maxResults, int firstResult) {
        return findSlideEntities(false, maxResults, firstResult);
    }

    private List<Slide> findSlideEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from Slide as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<Topic> findTopics() {

        EntityManager em = emf.createEntityManager();
        topics = em.createQuery("select t from Topic t").getResultList();
        em.close();
        return topics;

    }

    public List<Advertisement> findAdvertisements() {

        EntityManager em = emf.createEntityManager();
        advertisements = em.createQuery("select t from Advertisement t").getResultList();
        em.close();
        return advertisements;

    }

    public List<Advertisement> findAdvertisementsForType(String type) {

        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select t from Advertisement t WHERE t.type=:type");
        q.setParameter("type", type);
        em.close();
        return q.getResultList();

    }

    public WebSite findWebSite() {

        EntityManager em = emf.createEntityManager();
        websites = em.createQuery("select t from WebSite t").getResultList();
        em.close();
        return websites.get(0);

    }

    public Admin adminLogin(String name, String password) {
        Admin result;
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select a from Admin a where a.name=:name");
        q.setParameter("name", name);
        if (q.getResultList().size() > 0) {
            result = (Admin) q.getResultList().get(0);
            if (result.getPassword().equals(password)) {
                return result;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    public Admin adminLogin(int id, String password) {
        EntityManager em = emf.createEntityManager();
        Admin result = em.find(Admin.class, id);
        if (result.getPassword().equals(password)) {
            return result;
        } else {
            return null;
        }


    }

    public List<Image> findListImages() {

        EntityManager em = emf.createEntityManager();
        List<Image> image = em.createQuery("select t from Image t").getResultList();
        em.close();
        return image;

    }

    public List<Topic> findTopics(String parent) {

        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select t from Topic t where t.upperTopic=:parent");
        q.setParameter("parent", parent);
        topics = q.getResultList();
        em.close();
        return topics;

    }

    public Topic findTopicWithName(String name) {
        Topic tpc;
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select t from Topic t where t.name=:name");
        q.setParameter("name", name);
        tpc = (Topic) q.getResultList().get(0);
        em.close();
        return tpc;
    }

    public News findNewsWithName(String name) {
        News result = null;
        if (name.trim() != "" && name != null) {

            EntityManager em = emf.createEntityManager();
            Query q = em.createQuery("select a from News a where a.name=:name");
            q.setParameter("name", name);
            if (q.getResultList().size() > 0) {
                result = (News) q.getResultList().get(0);
            }
            em.close();
            return result;
        }
        return null;
    }

    public News findNews(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(News.class, id);
        } finally {
            em.close();
        }
    }

    public Admin findAdmin(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Admin.class, id);
        } finally {
            em.close();
        }
    }

    public Advertisement findAdvertisement(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Advertisement.class, id);
        } finally {
            em.close();
        }
    }

    public Topic findTopic(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Topic.class, id);
        } finally {
            em.close();
        }
    }

    public Article findArticle(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Article.class, id);
        } finally {
            em.close();
        }
    }

    public Link findLink(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Link.class, id);
        } finally {
            em.close();
        }
    }

    public Video findVideo(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Video.class, id);
        } finally {
            em.close();
        }
    }

    public Poll findPoll(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Poll.class, id);
        } finally {
            em.close();
        }
    }

    public Slide findSlide(int id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Slide.class, id);
        } finally {
            em.close();
        }
    }

    public List<News> findLastLikeArticleNewsEntitiesWithTopicName(String topic, String key, Date d1, Date d2) {
        return findLastLikeArticleNewsEntitiesWithTopicName(true, -1, -1, topic, key, d1, d2);
    }

    public List<News> findLastLikeArticleNewsEntitiesWithTopicName(int maxResults, int firstResult, String topic, String key, Date d1, Date d2) {
        return findLastLikeArticleNewsEntitiesWithTopicName(false, maxResults, firstResult, topic, key, d1, d2);
    }

    private List<News> findLastLikeArticleNewsEntitiesWithTopicName(boolean all, int maxResults, int firstResult, String topic, String key, Date d1, Date d2) {
        EntityManager em = getEntityManager();
        System.out.println(d1);
        System.out.println(d2);
        if (d1 == null) {
            d1 = new Date();
        }
        if (d2 == null) {
            d2 = new Date();
            d2.setYear(1);
        }
        System.out.println(d1);
        System.out.println(d2);
        try {
            Query q = em.createQuery("select object(o) from News as o where o.article!= null and o.topic.name=:parent and o.date BETWEEN :d1 AND :d2 and o.article.text Like :keyword or o.name Like :keyword or o.header Like :keyword ORDER BY o.date DESC");
            q.setParameter("parent", topic);
            q.setParameter("keyword", "%" + key + "%");
            q.setParameter("d1", new java.sql.Date(d1.getTime()), TemporalType.DATE);
            q.setParameter("d2", new java.sql.Date(d2.getTime()), TemporalType.DATE);


            System.out.println(new java.sql.Date(d1.getTime()));
            System.out.println(new java.sql.Date(d2.getTime()));

            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public List<News> findLastLikeArticleNewsEntities(String key, Date d1, Date d2) {
        return findLastLikeArticleNewsEntities(true, -1, -1, key, d1, d2);
    }

    public List<News> findLastLikeArticleNewsEntities(int maxResults, int firstResult, String key, Date d1, Date d2) {
        return findLastLikeArticleNewsEntities(false, maxResults, firstResult, key, d1, d2);
    }

    private List<News> findLastLikeArticleNewsEntities(boolean all, int maxResults, int firstResult, String key, Date d1, Date d2) {
        EntityManager em = getEntityManager();
       

        if (d1 == null) {
            d1 = new Date();
        }
        if (d2 == null) {
            d2 = new Date();
            d2.setYear(1);
       }

        try {
            Query q = em.createQuery("select object(o) from News as o where o.date BETWEEN :d1 AND :d2 and o.article.text Like :keyword or o.name Like :keyword or o.explainText Like :keyword or o.header Like :keyword ORDER BY o.date DESC");
            q.setParameter("keyword", "%" + key + "%");
            q.setParameter("d1", new java.sql.Date(d1.getYear(),d1.getMonth(),d1.getDay()), TemporalType.DATE);
            q.setParameter("d2", new java.sql.Date(d2.getYear(),d2.getMonth(),d2.getDay()), TemporalType.DATE);
            System.out.println(new java.sql.Date(d1.getYear(),d1.getMonth(),d1.getDay()));
            System.out.println(new java.sql.Date(d2.getYear(),d2.getMonth(),d2.getDay()));
            System.out.println(d1);
            System.out.println(d2);
          if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    
    public List<News> findLastLikeArticleNewsEntities(String key) {
        return findLastLikeArticleNewsEntities(true, -1, -1, key);
    }

    public List<News> findLastLikeArticleNewsEntities(int maxResults, int firstResult, String key) {
        return findLastLikeArticleNewsEntities(false, maxResults, firstResult, key);
    }

    private List<News> findLastLikeArticleNewsEntities(boolean all, int maxResults, int firstResult, String key) {
        EntityManager em = getEntityManager();
  

        try {
            Query q = em.createQuery("select object(o) from News as o where o.article.text Like :keyword or o.name Like :keyword or o.explainText Like :keyword or o.header Like :keyword ORDER BY o.date DESC");
            q.setParameter("keyword", "%" + key + "%");
         
          if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    //Functions of Finf with topicName
    public List<Poll> findNewsWithTopicName(String topicName) {

        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select p from News p where p.topic.name =:parent");
        q.setParameter("parent", topicName);
        polles = q.getResultList();
        em.close();
        return polles;

    }

    public Topic findTopicOfArticle(int id) {
        Topic tpc;
        EntityManager em = emf.createEntityManager();
        //Query q = em.createQuery("select a from Article a join fetch a.topic where a.id =:id");
        Query q = em.createQuery("select a from Article a join fetch a.topic where a.id =:id");
        q.setParameter("id", id);
        tpc = (Topic) q.getResultList().get(0);
        em.close();
        return tpc;

    }

    public Topic findTopicOfVideo(int id) {
        Topic tpc;
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select o.topic from Video o where o.id =:id");
        q.setParameter("id", id);
        tpc = (Topic) q.getResultList().get(0);
        em.close();
        return tpc;

    }

    public Topic findTopicOfSlide(int id) {
        Topic tpc;
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select o.topic from Slide o where o.id =:id");
        q.setParameter("id", id);
        tpc = (Topic) q.getResultList().get(0);
        em.close();
        return tpc;

    }

    public Topic findTopicOfPoll(int id) {
        Topic tpc;
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select o.topic from Poll o where o.id =:id");
        q.setParameter("id", id);
        tpc = (Topic) q.getResultList().get(0);
        em.close();
        return tpc;

    }

    public Topic findTopicOfLink(int id) {
        Topic tpc;
        EntityManager em = emf.createEntityManager();
        Query q = em.createQuery("select o.topic from Link o where o.id =:id");
        q.setParameter("id", id);
        tpc = (Topic) q.getResultList().get(0);
        em.close();
        return tpc;

    }

    public int getTopicCount(String name) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select t from Topic t where t.name=:name");
            q.setParameter("name", name);
            return q.getResultList().size();
        } finally {
            em.close();
        }
    }

    public int getNewsCount(String name) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select a from News a where a.name=:name");
            q.setParameter("name", name);
            return q.getResultList().size();
        } finally {
            em.close();
        }
    }

    public int getAdminCountWithName(String name) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select a from Admin a where a.name=:name");
            q.setParameter("name", name);
            return q.getResultList().size();
        } finally {
            em.close();
        }
    }

    public int getAdminCountWithEmail(String email) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select a from Admin a where a.email=:email");
            q.setParameter("email", email);
            return q.getResultList().size();
        } finally {
            em.close();
        }
    }

    //Functions of Edit
    public void editAdmin(Admin admin) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            admin = em.merge(admin);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = admin.getId();
                if (findAdmin(id) == null) {
                    throw new NonexistentEntityException("The admin with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void editTopic(Topic topic) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            topic = em.merge(topic);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = topic.getId();
                if (findAdmin(id) == null) {
                    throw new NonexistentEntityException("The topic with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void editWebsite(WebSite ws) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ws = em.merge(ws);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = ws.getId();
                if (findAdmin(id) == null) {
                    throw new NonexistentEntityException("The website with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }


    }

    public void editArticle(Article article) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            article = em.merge(article);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = article.getId();
                if (findAdmin(id) == null) {
                    throw new NonexistentEntityException("The link with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }


    }

    public void editLink(Link link) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            link = em.merge(link);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = link.getId();
                if (findAdmin(id) == null) {
                    throw new NonexistentEntityException("The link with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void editVideo(Video video) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            video = em.merge(video);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = video.getId();
                if (findAdmin(id) == null) {
                    throw new NonexistentEntityException("The video with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void editPoll(Poll poll) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            poll = em.merge(poll);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = poll.getId();
                if (findAdmin(id) == null) {
                    throw new NonexistentEntityException("The poll with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void editSlide(Slide slide) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            slide = em.merge(slide);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = slide.getId();
                if (findAdmin(id) == null) {
                    throw new NonexistentEntityException("The slide with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void editNews(News news) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            news = em.merge(news);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = news.getId();
                if (findAdmin(id) == null) {
                    throw new NonexistentEntityException("The news with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void editWebSite(WebSite webSite) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            webSite = em.merge(webSite);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = webSite.getId();
                if (findAdmin(id) == null) {
                    throw new NonexistentEntityException("The webSite with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void editAdvertisement(Advertisement advertisement) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            advertisement = em.merge(advertisement);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                int id = advertisement.getId();
                if (findAdmin(id) == null) {
                    throw new NonexistentEntityException("The advertisement with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    //Functions of Destroy
    public void destroyAdmin(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Admin admin;
            try {
                admin = em.getReference(Admin.class, id);
                admin.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The admin with id " + id + " no longer exists.", enfe);
            }
            em.remove(admin);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroyVideo(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Video video;
            try {
                video = em.getReference(Video.class, id);
                video.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The video with id " + id + " no longer exists.", enfe);
            }
            em.remove(video);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroyArticle(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Article article;
            try {
                article = em.getReference(Article.class, id);
                article.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The article with id " + id + " no longer exists.", enfe);
            }
            em.remove(article);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroyLink(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Link link;
            try {
                link = em.getReference(Link.class, id);
                link.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The link with id " + id + " no longer exists.", enfe);
            }
            em.remove(link);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroyPoll(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Poll poll;
            try {
                poll = em.getReference(Poll.class, id);
                poll.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The poll with id " + id + " no longer exists.", enfe);
            }
            em.remove(poll);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroySlide(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Slide slide;
            try {
                slide = em.getReference(Slide.class, id);
                slide.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The slide with id " + id + " no longer exists.", enfe);
            }
            em.remove(slide);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroyTopic(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Topic topic;
            try {
                topic = em.getReference(Topic.class, id);
                topic.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The topic with id " + id + " no longer exists.", enfe);
            }
            em.remove(topic);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroyImage(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Image image;
            try {
                image = em.getReference(Image.class, id);
                image.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The image with id " + id + " no longer exists.", enfe);
            }
            em.remove(image);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroyNews(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            News news;
            try {
                news = em.getReference(News.class, id);
                news.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The news with id " + id + " no longer exists.", enfe);
            }
            em.remove(news);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroyAdvertisement(int id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Advertisement advertisement;
            try {
                advertisement = em.getReference(Advertisement.class, id);
                advertisement.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The advertisement with id " + id + " no longer exists.", enfe);
            }
            em.remove(advertisement);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}
