package com.cajr.vo.user;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author CAJR
 * @date 2020/4/21 3:59 下午
 */
public class UserOther {
    private int id;

    private String username;

    private String avatar;

    private String signature;

    private int isFollow;

    @ApiModelProperty(hidden = true)
    private int followNum;

    @ApiModelProperty(hidden = true)
    private int followedNum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public int getIsFollow() {
        return isFollow;
    }

    public void setIsFollow(int isFollow) {
        this.isFollow = isFollow;
    }

    public int getFollowNum() {
        return followNum;
    }

    public void setFollowNum(int followNum) {
        this.followNum = followNum;
    }

    public int getFollowedNum() {
        return followedNum;
    }

    public void setFollowedNum(int followedNum) {
        this.followedNum = followedNum;
    }
}
