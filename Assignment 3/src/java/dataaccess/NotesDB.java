package dataaccess;

import domainmodel.Note;
import domainmodel.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class NotesDB {

    public int insert(Note note) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        User owner = note.getOwner();
        owner.getNoteList().add(note);
        
        try {
            trans.begin();
            em.persist(note);
            em.merge(owner);
            trans.commit();
            return 1;
        } catch (Exception ex) {
            trans.rollback();
            Logger.getLogger(NotesDB.class.getName()).log(Level.SEVERE, "Cannot insert " + note.toString(), ex);
            throw new NotesDBException("Error inserting user");
        } finally {
            em.close();
        }
    }

    public int update(Note note) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        
        try{
            trans.begin();
            em.merge(note);
            trans.commit();
            return 1;
        } catch (Exception ex) {
            trans.rollback();
            Logger.getLogger(NotesDB.class.getName()).log(Level.SEVERE, "Cannot update " + note.toString(), ex);
            throw new NotesDBException("Error inserting user");
        } finally {
            em.close();
        }
        
    }

    public List<Note> getAll() throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try{
            List<Note> notes = em.createNamedQuery("Note.findAll", Note.class).getResultList();
            return notes;
        }catch (Exception ex) {
            Logger.getLogger(NotesDB.class.getName()).log(Level.SEVERE, "Cannot read notes", ex);
            throw new NotesDBException("Error getting Notes");
        } finally {
            em.close();
        }
    }
    
    public Note getNoteId(int noteId) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        try {
            Note note = em.find(Note.class, noteId);
            return note;
        } catch (Exception ex) {
            Logger.getLogger(NotesDB.class.getName()).log(Level.SEVERE, "Cannot read users", ex);
            throw new NotesDBException("Error getting Users");
        } finally {
            em.close();
        }
    }

    public int delete(Note note) throws NotesDBException {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        User owner = note.getOwner();
        owner.getNoteList().remove(note);
        
        try {
            trans.begin();
            em.remove(em.merge(note));
            em.merge(owner);
            trans.commit();
            return 1;
        } catch (Exception ex) {
            trans.rollback();
            Logger.getLogger(NotesDB.class.getName()).log(Level.SEVERE, "Cannot delete " + note.toString(), ex);
            throw new NotesDBException("Error deleting user");
        } finally {
            em.close();
        }
    }
}