package com.example.filmorate;

import com.example.filmorate.storage.dao.impl.UserDbStorage;
import com.example.filmorate.storage.model.User;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@AutoConfigureTestDatabase
@RequiredArgsConstructor(onConstructor_ = @Autowired)
class FilmorateApplicationTests {
	private final UserDbStorage userDbStorage;

	@Test
	public void testAddUser() {
		User newUser = new User("zh_and@mail.ru", "Goodsn", "Andrey",
				LocalDate.of(1991,11,25));

		userDbStorage.add(newUser);
		Optional<User> userOptional = userDbStorage.get(1);

		assertThat(userOptional)
				.isPresent()
				.hasValueSatisfying(user ->
						assertThat(user).hasFieldOrPropertyWithValue("id", 1))
				.hasValueSatisfying(user ->
						assertThat(user).hasFieldOrPropertyWithValue("email", "zh_and@mail.ru"))
				.hasValueSatisfying(user ->
						assertThat(user).hasFieldOrPropertyWithValue("login", "Goodsn"))
				.hasValueSatisfying(user ->
						assertThat(user).hasFieldOrPropertyWithValue("name", "Andrey"))
				.hasValueSatisfying(user ->
						assertThat(user).hasFieldOrPropertyWithValue("birthday",
								LocalDate.of(1991,11,25)));
	}

	@Test
	public void testDeleteUser() {
		userDbStorage.delete(1);
		Optional<User> userOptional = userDbStorage.get(1);

		assertThat(userOptional)
				.isEmpty();
	}

//	@Test
//	public void testFindUserById() {
//		Optional<User> userOptional = userDbStorage.get(1);
//		assertThat(userOptional)
//				.isPresent()
//				.hasValueSatisfying(user ->
//						assertThat(user).hasFieldOrPropertyWithValue("id", 1));
//	}



}
