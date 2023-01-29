package jp.co.planaria.sample.motocatalog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import jp.co.planaria.sample.motocatalog.beans.User;
import jp.co.planaria.sample.motocatalog.mappers.UserMapper;


@Component
public class UserService implements UserDetailsService {

  @Autowired
  UserMapper userMapper;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    // SpringBootでのパスワードはハッシュ化しないと使用できない
    // return new User("test", "$2a$10$6nxIrFGcF1SlLq1B5P/Bn.vKBjM4K8/OqqS9C.F6JpI4OvgZvoNf6");

    User user = userMapper.selectByUsername(username);
    if (user == null) {
      throw new UsernameNotFoundException("ユーザーが存在しません。");
    }
    return user;
  }

}
