/*
 ************************************************************************************
 * Copyright (C) 2001-2010 encuestame: system online surveys Copyright (C) 2009
 * encuestame Development Team.
 * Licensed under the Apache Software License version 2.0
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to  in writing,  software  distributed
 * under the License is distributed  on  an  "AS IS"  BASIS,  WITHOUT  WARRANTIES  OR
 * CONDITIONS OF ANY KIND, either  express  or  implied.  See  the  License  for  the
 * specific language governing permissions and limitations under the License.
 ************************************************************************************
 */
package org.encuestame.business.social;

import org.encuestame.core.exception.EnMeNoSuchAccountConnectionException;
import org.encuestame.persistence.domain.security.AccountConnection;
import org.encuestame.persistence.domain.security.UserAccount;
import org.encuestame.persistence.exception.EnMeDomainNotFoundException;
import org.encuestame.persistence.exception.EnMeExpcetion;
import org.encuestame.utils.oauth.AuthorizedRequestToken;
import org.encuestame.utils.oauth.OAuthToken;

/**
 * Description Class.
 * @author Picado, Juan juanATencuestame.org
 * @since Dec 25, 2010 2:11:24 AM
 * @version Id:
 */
public interface SocialProviderOperations<S> {

        /**
         * The unique name or id of the service provider e.g. twitter.
         * Unique across all service providers.
         */
        String getName();

        /**
         * A label suitable for display in a UI, typically used to inform the user which service providers he or she has connected with / may connect with. e.g. Twitter.
         */
        String getDisplayName();

        /**
         * The key used to identify the local application with the remote service provider.
         * Used when establishing an account connection with the service provider.
         * Available as a public property to support client code that wishes to manage the service connection process itself, for example, in JavaScript.
         * The term "API key" is derived from the OAuth 2 specification.
         */
        String getApiKey();

        /**
         * An alternate identifier for the local application in the remote service provider's system.
         * May be null of no such alternate identifier exists.
         * Used by ServiceProvider&lt;FacebookOperations&gt; to support "Like" functionality.
         * @return an alternate app id, or null if no alternate id exists (null is the typical case, as the {@link #getApiKey()} is the primary means of consumer identification)
         */
        Long getAppId();

        // connection management

        /**
         * Begin the account connection process by fetching a new request token from this service provider.
         * The new token should be stored in the member's session up until the authorization callback is made and it's time to {@link #connect(Long, AuthorizedRequestToken) connect}.
         * @param callbackUrl the URL the provider should redirect to after the member authorizes the connection (may be null for OAuth 1.0-based service providers)
         */
        OAuthToken fetchNewRequestToken(String callbackUrl);

        /**
         * Construct the URL to redirect the member to for connection authorization.
         * @param requestToken the request token value, to be encoded in the authorize URL
         * @return the absolute authorize URL to redirect the member to for authorization
         */
        String buildAuthorizeUrl(String requestToken);

        /**
         * Connects a member account to this service provider.
         * Called after the user authorizes the connection at the {@link #buildAuthorizeUrl(String) authorizeUrl} and the service provider calls us back.
         * Internally, exchanges the authorized request token for an access token, then stores the awarded access token with the member account.
         * This access token identifies the connection between the member account and this service provider.
         * <p>
         * This method completes the OAuth-based account connection process.
         * {@link #getServiceOperations(Long)} may now be called to get and invoke the service provider's API.
         * The requestToken required during the connection handshake is no longer valid and cannot be reused.
         * @param accountId the member account identifier
         * @param requestToken the OAuth request token that was authorized by the member.
         * @return
         * @throws EnMeExistPreviousConnectionException
         */
        void connect(Long accountId, AuthorizedRequestToken requestToken) throws EnMeExistPreviousConnectionException;

        /**
         * Records an existing connection between a member account and this service provider.
         * Use when the connection process happens outside of the control of this package; for example, in JavaScript.
         * @param accountId the member account identifier
         * @param accessToken the access token that was granted as a result of the connection
         * @param providerAccountId the id of the user in the provider's system; may be an assigned number or a user-selected screen name.
         */
        void addConnection(Long accountId, String accessToken, String providerAccountId);

        /**
         * Returns true if the member account is connected to this provider, false otherwise.
         */
        boolean isConnected(Long accountId);

        /**
         * Sever the connection between the member account and this service provider.
         * Has no effect if no connection is established to begin with.
         */
        void disconnect(Long accountId);

        // additional finders

        /**
         * The id of the member in the provider's system.
         * May be an assigned internal identifier, such as a sequence number, or a user-selected screen name.
         * Generally unique across accounts registered with this provider.
         */
        //String getProviderAccountId(Long accountId);

        /**
         * Authenticate a member Account by a connection established with this service provider.
         * Used to support "Sign in using Facebook"-type scenarios, where the access token identifying a connection is available to client code, typically a cookie managed by JavaScript.
         * @throws NoSuchAccountConnectionException no such connection has been established between a member and this service provider
         */
        UserAccount findAccountByConnection(String accessToken) throws EnMeDomainNotFoundException;

        /**
         * Find the members connected to this provider that have the specified account ids in the provider's system.
         * Used to see which of your friends in the provider's network also have member accounts with the local application.
         */
        //List<ProfileReference> findMembersConnectedTo(List<String> providerAccountIds);

}
