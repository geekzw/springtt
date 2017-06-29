package com.gzw.mappers;

import com.gzw.domain.User;
import org.springframework.stereotype.Repository;

/**
 * Created by gujian on 2017/6/24.
 */
public interface UserMapper extends BaseMapper<User>{

    User findByUsername(String usernmae);

}
