package edu.sucho.libreriaweb.service.impl;


import edu.sucho.libreriaweb.mapper.UserDetailsMapper;
import edu.sucho.libreriaweb.model.entity.Usuario;
import edu.sucho.libreriaweb.repository.BaseRepository;
import edu.sucho.libreriaweb.repository.ClienteRepository;
import edu.sucho.libreriaweb.repository.RolRepository;
import edu.sucho.libreriaweb.service.inter.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl  implements UserService {

	@Autowired
	private RolRepository roleRepository;
	@Autowired
	private ClienteRepository clienteRepository;


	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		final Usuario retrievedUser = clienteRepository.findByUsername(userName).get();
		if (retrievedUser == null) {
			throw new UsernameNotFoundException("Invalid username or password");
		}

		return UserDetailsMapper.build(retrievedUser);
	}

	@Override
	public Usuario getUsuario(Integer id) {
		return clienteRepository.findById(id).get();
	}

//	@Override
//	public Usuario getUsuario(Integer id) {
//		return clienteRepository.findById(id);
//	}
//
//	@Override
//	public User save(User user) {
//		Role userRole = roleRepository.findByName("USER");
//		Set<Role> roles = new HashSet<>();
//		roles.add(userRole);
//
//		User userToSave = User.builder().name(user.getName()).password(user.getPassword()).roles(roles).build();
//
//		return userRepository.save(userToSave);
//	}
}
