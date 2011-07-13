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
package org.encuestame.persistence.domain;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.encuestame.persistence.domain.security.UserAccount;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;


/**
 * Hash Tags Hits.
 * @author Morales, Diana Paola paolaATencuestame.org
 * @since Jul 23, 2010 11:49:56 PM
 * @version Id:
 */

@Entity
@Indexed(index="HashTagHits")
@Table(name = "hash_tags_hits")
public class HashTagHits {

    /** Hash Tag Id. **/
    private Long hitId;

    /** Tag String.**/
    private Date hitDate;

    /** Hash**/
    private HashTag hashTagId;

    /** Ip address**/
    private String ipAddress;

    /** {@link UserAccount} **/
    private UserAccount userAccount;

    /**
    * @return the hitId
    */
    @Id
    @DocumentId
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "hash_tag_id", unique = true, nullable = false)
    public Long getHitId() {
        return hitId;
    }

    /**
    * @param hitId the hitId to set
    */
    public void setHitId(final Long hitId) {
        this.hitId = hitId;
    }

    /**
    * @return the hitDate
    */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "hitDate", nullable = false)
    public Date getHitDate() {
        return hitDate;
    }

    /**
    * @param hitDate the hitDate to set
    */
    public void setHitDate(final Date hitDate) {
        this.hitDate = hitDate;
    }

    /**
    * @return the hashTagId
    */
    @ManyToOne()
    public HashTag getHashTagId() {
        return hashTagId;
    }

    /**
    * @param hashTagId the hashTagId to set
    */
    public void setHashTagId(final HashTag hashTagId) {
        this.hashTagId = hashTagId;
    }

    /**
    * @return the ipAddress
    */
    @Field(index=Index.TOKENIZED, store=Store.YES)
    @Column(name = "ip_address", nullable = false, length = 100)
    public String getIpAddress() {
        return ipAddress;
    }

    /**
    * @param ipAddress the ipAddress to set
    */
    public void setIpAddress(final String ipAddress) {
        this.ipAddress = ipAddress;
    }

    /**
    * @return the userAccount
    */
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user")
    public UserAccount getUserAccount() {
        return userAccount;
    }

    /**
    * @param userAccount the userAccount to set
    */
    public void setUserAccount(final UserAccount userAccount) {
        this.userAccount = userAccount;
    }

}
