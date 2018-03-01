package com.zjw.dr.constant;

/**
 * Created by 祝锦伟 on 2017/10/16.
 */

public interface Constants {


    interface URL{


        String REDIRECT_URL="http://example.com/path";

        String BASE_URL="https://api.dribbble.com/v1/";

        String NEW_BASE_URL="http://api.dribbble.com/v2/";

        String OAUTH_URL="https://dribbble.com/oauth/";

        String AUTHORIZE_URL="https://dribbble.com/oauth/authorize?client_id=%s&scope=%s&redirect_uri=%s&state=%s";

        String TEST_NORMAL_IMAGE_URL="https://d13yacurqjgara.cloudfront.net/users/1/screenshots/471756/sasquatch.png";

        String TEST_TEASER_IMAGE_URL="https://d13yacurqjgara.cloudfront.net/users/1/screenshots/471756/sasquatch_teaser.png";
    }

    interface PATH{

        String TOKEN="token";

        String AUTHORIZE="authorize";

        String USER="user";

        String USER_SHOTS = "users/{id}/shots";

        String USER_FOLLOWERS="users/{user}/followers";

        String USER_FOLLOWING="users/{user}/following";

        String USER_FOLLOWING_SHOTS="{user}/following/shots";

        String USER_BUCKETS="users/{user}/buckets";

        String FOLLOWING_CHECK="user/following/{user}";

        String USER_FOLLOING_CHECK="users/{user}/following/{target_user}";

        String USER_LIKE_SHOTS="users/{user}/likes";

        String USER_PROJECTS="users/{user}/projects";

        String PROJECTS_SHOTS="projects/{id}/shots";

        String FOLLOW_USER="users/{user}/follow";

        String SHOTS="shots";

        String ADD_SHOTS_TO_BUCKETS = "buckets/{id}/shots";

        String SINGLE_SHOT="shots/{id}";

        String SHOTS_ATTACHMENT="shots/{id}/attachments";

        String SHOTS_BUCKETS="shots/{id}/buckets";

        String SHOTS_COMMENTS="shots/{shot}/comments";

        String COMMENT_LIKES="shots/{shot}/comments/{id}/likes";

        String SINGLE_COMMENT="shots/{shot}/comments/{id}";

        String CREATE_COMMENT="shots/{shot}/comments";

        String COMMENT_CHECK_LIKE="shots/{shot}/comments/{id}/like";

        String SHOTS_LIKES="shots/{id}/likes";

        String SHOTS_CHECK_LIKE="shots/{id}/like";

    }


    interface PARAMETER{

        String CLIENT_ID="8d61ead256967c54e77bfa743238d1b616ffdc6b00c79fc3d50049e4b88facc2";

        String CLIENT_SECRET="470a7ef541283042a455f0fa47c38f79506e691e98692a486b66c19f57eebec7";

        String TOKEN="4a56859439f9a06219c8499132ded039f505af2e7aff0d16ca22e52f5c8b3650";

        String SCOPES="public+write+comment+upload";

        String STATE="zjw";

        String[] SHOT_LIST_TYPE={"","animated","attachments","debuts","playoffs","rebounds","teams"};

        String[] SHOT_LIST_TIMEFRAME={"","week","month","year","ever"};

        String[] SHOT_LIST_SORT={"","comments","recent","views"};

        int VIEW_WITH_INFO=0;

        int VIEW_NO_INFO=1;


    }


    interface KEYS{

        String CLIENT_ID="client_id";

        String CLIENT_SECRET="client_secret";

        String REDIRECT_URL="redirect_url";

        String ACCESS_TOKEN="access_token";

        String TOKEN_TYPE="token_type";

        String SCOPE="scope";

        String CODE="code";

        String VIEW_MODE="view_mode";

        String IS_AUTH_SUCCESS="is_auth_success";

        String IS_SHOW_GIF="is_show_gif";

        String IS_FIRST_RUN="is_first_run";

        String SHOT_LIST_TYPE="list";

        String SHOT_LIST_TIMEFRAME="timeframe";

        String SHOT_LIST_DATE="date";

        String SHOT_LIST_SORT="sort";

        String PAGE="page";

        String PAGE_SIZE="per_page";

        String CURRENT_USER="current_user";

        String SHOT="shot";

        String MY_ACCOUNT="my_account";

        String USER_ENTITY="user_entity";

        String SHOT_DETAIL="shot_detail";

    }

    interface APP{

        String IS_FIRST_LOGIN="is_first_login";


    }



}
