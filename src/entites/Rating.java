/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entites;

import entites.Post;
import java.util.Date;

/**
 *
 * @author MSI
 */
public class Rating {

    private int id, rate;
    private Post post;
    private User membre;
    private Date created_at;

    public Rating() {
    }

    public Rating(int id, int rate, Post post, User membre, Date created_at) {
        this.id = id;
        this.rate = rate;
        this.post = post;
        this.membre = membre;
        this.created_at = created_at;
    }

    public Rating(int id, int rate, Post post, User membre) {
        this.id = id;
        this.rate = rate;
        this.post = post;
        this.membre = membre;
    }

    @Override
    public String toString() {
        return "Rating{" + "id=" + id + ", rate=" + rate + ", post=" + post + ", membre=" + membre + ", created_at=" + created_at + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public User getMembre() {
        return membre;
    }

    public void setMembre(User membre) {
        this.membre = membre;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

}
