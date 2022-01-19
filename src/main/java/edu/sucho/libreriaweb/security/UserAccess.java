package edu.sucho.libreriaweb.security;

import edu.sucho.libreriaweb.exception.ExceptionBBDD;
import edu.sucho.libreriaweb.service.inter.ClienteService;
import edu.sucho.libreriaweb.service.inter.PrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component("userAccess")
public class UserAccess {
    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PrestamoService prestamoService;

    public boolean hasUserId(Authentication authentication, int userId) {
        String role = authentication.getAuthorities().stream().findFirst().get().toString();
        if (userId != clienteService.findByUsername(authentication.getName()).getId() && role.equals("ROLE_CLIENTE")) {
            return false;
        }
        return true;
    }

    public boolean userIdByPrestamoId(Authentication authentication, int prestamoId) throws ExceptionBBDD {
        int userId =  prestamoService.findById(prestamoId).getCliente().getId();
        return hasUserId(authentication, userId);
    }

}