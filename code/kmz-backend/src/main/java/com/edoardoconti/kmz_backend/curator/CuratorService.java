package com.edoardoconti.kmz_backend.curator;

import com.edoardoconti.kmz_backend.role.UserRoleType;
import com.edoardoconti.kmz_backend.user.UserDTO;
import com.edoardoconti.kmz_backend.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CuratorService {
    private final UserService userService;

    public Long requestCurator() {
         List<UserDTO> curators = userService.getUsers()
                 .stream()
                 .filter(user -> user.getRoles().contains(UserRoleType.CURATOR))
                 .toList();

         // TODO: some assignment logic
         UserDTO assignedCurator = curators.get((int)Math.floor(Math.random() * curators.size()));
         return assignedCurator.getId();
    }
}
