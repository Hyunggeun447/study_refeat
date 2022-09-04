package solo.studyRefeat.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import solo.studyRefeat.domain.user.entity.User;
import solo.studyRefeat.domain.user.repository.UserRepository;

@Transactional
@Service
@RequiredArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public User checkUser(User user) {
    return userRepository.findById(user.getId())
        .orElseThrow(RuntimeException::new);
  }

}
