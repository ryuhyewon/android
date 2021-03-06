
package com.mapia.asne.core.listener.base;

/**
 * Base interface definition for a callback to be invoked when any social network request complete.
 *
 * @author Anton Krasov
 * @author Evgeny Gorbin (gorbin.e.o@gmail.com)
 *
 * @see com.mapia.asne.core.listener.OnCheckIsFriendCompleteListener
 * @see com.mapia.asne.core.listener.OnLoginCompleteListener
 * @see com.mapia.asne.core.listener.OnPostingCompleteListener
 * @see com.mapia.asne.core.listener.OnRequestAccessTokenCompleteListener
 * @see com.mapia.asne.core.listener.OnRequestAddFriendCompleteListener
 * @see com.mapia.asne.core.listener.OnRequestDetailedSocialPersonCompleteListener
 * @see com.mapia.asne.core.listener.OnRequestGetFriendsCompleteListener
 * @see com.mapia.asne.core.listener.OnRequestRemoveFriendCompleteListener
 * @see com.mapia.asne.core.listener.OnRequestSocialPersonCompleteListener
 * @see com.mapia.asne.core.listener.OnRequestSocialPersonsCompleteListener
 */
public interface SocialNetworkListener {
    /**
     * Called when social network request complete with error.
     * @param socialNetworkID id of social network where request was complete with error
     * @param requestID id of request where request was complete with error
     * @param errorMessage error message where request was complete with error
     * @param data data of social network where request was complete with error
     */
    public void onError(int socialNetworkID, String requestID, String errorMessage, Object data);
}
