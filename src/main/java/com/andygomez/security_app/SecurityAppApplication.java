package com.andygomez.security_app;

import com.andygomez.security_app.flow.model.PermissionModel;
import com.andygomez.security_app.flow.model.RoleEnum;
import com.andygomez.security_app.flow.model.RoleModel;
import com.andygomez.security_app.flow.model.UserModel;
import com.andygomez.security_app.flow.model.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SecurityAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityAppApplication.class, args);
	}

	@Bean
	CommandLineRunner init(UserRepository repository){
		return args -> {
			/* Create PERMISSIONS */
			PermissionModel createPermission = PermissionModel.builder()
					.name("CREATE")
					.build();

			PermissionModel readPermission = PermissionModel.builder()
					.name("READ")
					.build();

			PermissionModel updatePermission = PermissionModel.builder()
					.name("UPDATE")
					.build();

			PermissionModel deletePermission = PermissionModel.builder()
					.name("DELETE")
					.build();

			PermissionModel refactorPermission = PermissionModel.builder()
					.name("REFACTOR")
					.build();

			/* Create ROLES */
			RoleModel roleAdmin = RoleModel.builder()
					.roleEnum(RoleEnum.ADMIN)
					.permissionModels(Set.of(createPermission, readPermission, updatePermission, deletePermission))
					.build();

			RoleModel roleUser = RoleModel.builder()
					.roleEnum(RoleEnum.USER)
					.permissionModels(Set.of(createPermission, readPermission))
					.build();

			RoleModel roleInvited = RoleModel.builder()
					.roleEnum(RoleEnum.GUEST)
					.permissionModels(Set.of(readPermission))
					.build();

			RoleModel roleDeveloper = RoleModel.builder()
					.roleEnum(RoleEnum.DEVELOPER)
					.permissionModels(Set.of(createPermission, readPermission, updatePermission, deletePermission, refactorPermission))
					.build();

			/* CREATE USERS */
			UserModel userSantiago = UserModel.builder()
					.username("santiago")
					.password("$2a$10$.bz2Y0tvksMiIQo0Gz.J.eKe3auedIu7M5rWqlEETp2V5Bc9ZotYG")
					.isEnable(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleAdmin))
					.build();

			UserModel userDaniel = UserModel.builder()
					.username("daniel")
					.password("$2a$10$.bz2Y0tvksMiIQo0Gz.J.eKe3auedIu7M5rWqlEETp2V5Bc9ZotYG")
					.isEnable(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleUser))
					.build();

			UserModel userAndrea = UserModel.builder()
					.username("andrea")
					.password("$2a$10$.bz2Y0tvksMiIQo0Gz.J.eKe3auedIu7M5rWqlEETp2V5Bc9ZotYG")
					.isEnable(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleInvited))
					.build();

			UserModel userAndy = UserModel.builder()
					.username("andy")
					.password("$2a$10$.bz2Y0tvksMiIQo0Gz.J.eKe3auedIu7M5rWqlEETp2V5Bc9ZotYG")
					.isEnable(true)
					.accountNoExpired(true)
					.accountNoLocked(true)
					.credentialNoExpired(true)
					.roles(Set.of(roleDeveloper))
					.build();

			repository.saveAll(List.of(userSantiago, userDaniel, userAndrea, userAndy));
		};
	}

}
