/**
 * Copyright © 2015 - 2017 EntDIY JavaEE Development Framework
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.entdiy.auth.dao;

import com.entdiy.auth.entity.Department;
import com.entdiy.core.dao.jpa.BaseDao;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentDao extends BaseDao<Department, Long> {

//    @QueryHints({@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value = "true")})
//    @Query("SELECT d.id as id, d.code as code, d.name as name, " +
//            "(select count(*) from Department dd where dd.parent=d.id and dd.disabled=false) as childrenCount " +
//            "FROM Department d where disabled=false and (code like %:keyword% or name like %:keyword%)")
//    List<Department.DepartmentTreeDataDto> findTreeDataByKeyword(@Param("keyword") String keyword);
//
//    @QueryHints({@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value = "true")})
//    @Query("SELECT d.id as id, d.code as code, d.name as name, " +
//            "(select count(*) from Department dd where dd.parent=d.id and dd.disabled=false) as childrenCount " +
//            "FROM Department d where disabled=false and parent is null")
//    List<Department.DepartmentTreeDataDto> findTreeDataRoots();
//
//    @QueryHints({@QueryHint(name = org.hibernate.jpa.QueryHints.HINT_CACHEABLE, value = "true")})
//    @Query("SELECT d from DepartmentTree d")
//    List<DepartmentTree> findTreeDataRootsss();


    interface DepartmentTree {
        String getCode();


        Integer getCount();
    }
}