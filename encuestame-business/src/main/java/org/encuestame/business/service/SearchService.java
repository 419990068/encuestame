/*
 ************************************************************************************
 * Copyright (C) 2001-2009 encuestame: system online surveys Copyright (C) 2009
 * encuestame Development Team.
 * Licensed under the Apache Software License version 2.0
 * You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to  in writing,  software  distributed
 * under the License is distributed  on  an  "AS IS"  BASIS,  WITHOUT  WARRANTIES  OR
 * CONDITIONS OF ANY KIND, either  express  or  implied.  See  the  License  for  the
 * specific language governing permissions and limitations under the License.
 ************************************************************************************
 */
package org.encuestame.business.service;

import java.util.List;

import org.encuestame.business.search.GlobalSearchItem;
import org.encuestame.business.service.imp.SearchServiceOperations;

/**
 * Search Service.
 *
 * @author Morales, Diana Paola paola AT encuestame.org
 * @since February 09, 2011
 * @version $Id$
 */
public class SearchService extends AbstractIndexService implements SearchServiceOperations {



    public List<GlobalSearchItem> quickSearch(String keyword) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<GlobalSearchItem> quickSearch(String keyword, String language) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<GlobalSearchItem> globalKeywordSearch(String keyword,
            String language) {
        // TODO Auto-generated method stub
        return null;
    }

    public List<GlobalSearchItem> globalKeywordSearch(String keyword) {
        // TODO Auto-generated method stub
        return null;
    }


}
