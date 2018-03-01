package com.zjw.dr.api;

import com.zjw.dr.constant.Constants;
import com.zjw.dr.entity.AccessToken;
import com.zjw.dr.entity.AttachmentEntity;
import com.zjw.dr.entity.CheckLikeEntity;
import com.zjw.dr.entity.FollowerEntity;
import com.zjw.dr.entity.LikesEntity;
import com.zjw.dr.entity.ShotEntity;
import com.zjw.dr.entity.ShotsBucketsEntity;
import com.zjw.dr.entity.ShotsCommentEntity;
import com.zjw.dr.entity.UserBucketEntity;
import com.zjw.dr.entity.UserEntity;
import com.zjw.dr.entity.UserLikeEntity;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by 祝锦伟 on 2017/10/16.
 */

public interface DRApi {


    //BaseUrl 为OAUTH_URL

    /**
     * 获取访问令牌
     * **/
    @FormUrlEncoded
    @POST(Constants.PATH.TOKEN)
    Flowable<AccessToken> getAccessToken(@Field("client_id") String clientId,
                                         @Field("client_secret") String clientSecret,
                                         @Field("code") String code,
                                         @Field("redirect_url") String redirectUrl);

    //BaseUrl 为BaseUrl
    /**
     * 获取验证用户信息
     * **/
    @GET(Constants.PATH.USER)
    Flowable<UserEntity> getAuthenticatedUser();

    /**
     * 获取用户的shots
     * **/
    @GET(Constants.PATH.USER_SHOTS)
    Flowable<List<ShotEntity>> getUserShots(@Path("id") String userId,@QueryMap Map<String,String> params) ;

    /**
     * 获取关注用户的follower列表
     * **/
    @GET(Constants.PATH.USER_FOLLOWERS)
    Flowable<List<FollowerEntity>> getFollowers(@Path("user") String user,@QueryMap Map<String,String> params);

    /**
     * 获取用户关注的following列表
     * **/
    @GET(Constants.PATH.USER_FOLLOWING)
    Flowable<List<FollowerEntity>> getFollowings(@Path("user") String user);

    /**
     * 获取用户的bucket列表
     * **/
    @GET(Constants.PATH.USER_BUCKETS)
    Flowable<List<UserBucketEntity>> getUserBuckets(@Path("user") String userId, @QueryMap Map<String,String> params);

    /**
     *获取用户关注的作品列表
     * **/
    @GET(Constants.PATH.USER_FOLLOWING_SHOTS)
    Flowable<List<ShotEntity>> getFollowingShots(@Path("user") String userId, @QueryMap Map<String,String > params);

    /**　
     * 获取用户like的shot 列表
     * **/
    @GET(Constants.PATH.USER_LIKE_SHOTS)
    Flowable<List<UserLikeEntity>> getUserLikeShots(@Path("user") String userId, @QueryMap Map<String,String> params);

    /**
     * 获取用户的project 列表
     * **/
    @GET(Constants.PATH.USER_PROJECTS)
    Flowable<List<UserBucketEntity>>  getUserProjects(@Path("user") String userId, @Query("page") String page);

    /**
     * 获取用户的project 的 shots 列表
     * **/
    @GET(Constants.PATH.PROJECTS_SHOTS)
    Flowable<List<ShotEntity>> getProjectShots(@Path("id") String projectId);

    /**
     *检测我是否已关注目标用户
     * **/
    @GET(Constants.PATH.FOLLOWING_CHECK)
    Flowable<String> checkFollowing(@Path("user") String user);

    /**
     * 检测用户A是否关注用户B
     * **/
    @GET(Constants.PATH.USER_FOLLOING_CHECK)
    Flowable<String> userCheckFollowing(@Path("user") String userA, @Path("target_user") String userB);

    /**
     * 关注用户
     * **/
    @PUT(Constants.PATH.FOLLOW_USER)
    Flowable<String> followUser(@Path("user") String user);

    /**
     * 取消关注
     * **/
    @DELETE(Constants.PATH.FOLLOW_USER)
    Flowable<String> unFollowUser(@Path("user") String user);


    /**
     * 获取作品列表
     * **/
    //TODO 验证 新版本使用日期，旧版本使用页码
    @GET(Constants.PATH.SHOTS)
    Flowable<List<ShotEntity>> getShotList(@QueryMap Map<String, String> params);


    /**
     * 获取一副shot 作品
     **/
    @GET(Constants.PATH.SINGLE_SHOT)
    Flowable<ShotEntity> getSingleShot(@Path("id") String id);

    /**
     * 收藏shot到 一个收藏夹
     * **/
    @PUT(Constants.PATH.ADD_SHOTS_TO_BUCKETS)
    Flowable<String> addShotsToBuckets(@Path("id") String bucketId,@Query("shot_id") String shotId);

    /**
     * 获取作品 Attachments 列表
     * */
    @GET(Constants.PATH.SHOTS_ATTACHMENT)
    Flowable<List<AttachmentEntity>> getShotsAttachments(@Path("id") String id);

    /**
     * 获取作品 的 buckets 列表
     * */
    @GET(Constants.PATH.SHOTS_BUCKETS)
    Flowable<List<ShotsBucketsEntity>> getShotsBuckets(@Path("id") String id);

    /**
     * 获取作品的 comments 列表
     * **/
    @GET(Constants.PATH.SHOTS_COMMENTS)
    Flowable<List<ShotsCommentEntity>> getShotComments(@Path("shot") String shot, @QueryMap Map<String,String> params);

    /**
     *获取 comment 的 likes 列表
     ***/
    @GET(Constants.PATH.COMMENT_LIKES)
    Flowable<List<LikesEntity>> getCommentLikes(@Path("shot") String shot, @Path("id") String id);

    /**
     *获取单一 comment
     ***/
    @GET(Constants.PATH.SINGLE_COMMENT)
    Flowable<ShotsCommentEntity> getSingleComment(@Path("shot") String shot, @Path("id") String id);

    /**
     * 创建一个 comment
     * **/
    @POST(Constants.PATH.CREATE_COMMENT)
    Flowable<ShotsCommentEntity> createSingleComment(@Path("shot") String shotId, @Query("body") String body);


    /**
     * 更新一个comment
     * **/
    @PUT(Constants.PATH.SINGLE_COMMENT)
    Flowable<ShotsCommentEntity> updateSingleComment(@Path("shot") String shot, @Path("id") String id);

    /**
     * 删除一个comment
     * **/
    @DELETE(Constants.PATH.SINGLE_COMMENT)
    Flowable<String> daleteSingleComment(@Path("shot") String shot, @Path("id") String id);

    /**
     * 检测是否like 一个 comment
     * **/
    @GET(Constants.PATH.COMMENT_CHECK_LIKE)
    Flowable<CheckLikeEntity> checkCommentLike(@Path("shot") String shot, @Path("id") String id);

    /**
     *对一个comment 进行like 操作
     ***/
    @POST(Constants.PATH.COMMENT_CHECK_LIKE)
    Flowable<CheckLikeEntity> likeComment(@Path("shot") String shot, @Path("id") String id);

    /**
     * 对一个comment 进行unlike 操作
     * **/
    @DELETE(Constants.PATH.COMMENT_CHECK_LIKE)
    Flowable<CheckLikeEntity> unlikeComment(@Path("shot") String shot, @Path("id") String id);

    /**
     * 获取作品的 likes 列表
     * **/
    @GET(Constants.PATH.SHOTS_LIKES)
    Flowable<List<LikesEntity>> getShotsLikes(@Path("id") String id);

    /**
     * 检验 验证用户对shot是否like
     * **/
    @GET(Constants.PATH.SHOTS_CHECK_LIKE)
    Flowable<CheckLikeEntity> checkShotsLike(@Path("id") String id);

    /**
     * 验证用户 like 操作
     * **/
    @POST(Constants.PATH.SHOTS_CHECK_LIKE)
    Flowable<CheckLikeEntity> likeShots(@Path("id") String id);

    /**
     * 验证用户 unlike 操作
     * **/
    @DELETE(Constants.PATH.SHOTS_CHECK_LIKE)
    Flowable<CheckLikeEntity> unlikeShots(@Path("id") String id);

}
