/**
 * TweetDTO.java
 * <p>
 * Author: avenarl
 * Created on: 05-17-23
 * <p>
 * Data Transfer Object for Tweet entity.
 * This is used to encapsulate the Tweet data and
 * prevent direct exposure of the domain entities
 * to the client.
 * <p>
 * Fields:
 * 1. id - the id of the tweet
 * 2. tweetContent - the content of the tweet
 * 3. tweetTimestamp - the timestamp of tweet
 * 4. userID - the user id of user
 */
package com.tweelon.dto;

import java.time.LocalDateTime;

public class TweetDTO {

    private Long id;
    private String tweetContent;
    private LocalDateTime tweetTimestamp;
    private Long userId;

    // Id

    /**
     * Gets the ID of the tweet.
     * @return the ID of the tweet.
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the ID of the tweet.
     * @param id the ID to set.
     */
    public void setId(Long id) {
        this.id = id;
    }

    // Tweet Content

    /**
     * Gets the content of the tweet.
     * @return the content of the tweet.
     */
    public String getTweetContent() {
        return tweetContent;
    }

    /**
     * Sets the content of the tweet.
     * @param tweetContent the content to set.
     */
    public void setTweetContent(String tweetContent) {
        this.tweetContent = tweetContent;
    }

    // Tweet Timestamp

    /**
     * Gets the timestamp of the tweet.
     * @return the timestamp of the tweet.
     */
    public LocalDateTime getTweetTimestamp() {
        return tweetTimestamp;
    }

    /**
     * Sets the timestamp of the tweet.
     * @param tweetTimestamp the timestamp to set.
     */
    public void setTweetTimestamp(LocalDateTime tweetTimestamp) {
        this.tweetTimestamp = tweetTimestamp;
    }

    // User ID

    /**
     * Gets the ID of the user who posted the tweet.
     * @return the user ID.
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Sets the ID of the user who posted the tweet.
     * @param userId the user ID to set.
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
