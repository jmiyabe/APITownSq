package com.betownsq.api;

import com.betownsq.api.controller.UserController;
import com.betownsq.api.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    public void setup(){
    }

    @Test
    public void getUserProps() throws Exception {
        when(userService.getUserProps("rodrigo.soares@gmail.com")).thenReturn("1;[(Reservas,Escrita),(Entregas,Nenhuma),(Usuarios,Escrita)] 2;[(Reservas,Escrita),(Entregas,Leitura),(Usuarios,Escrita)]");
        this.mockMvc.perform(get("/api/user/rodrigo.soares@gmail.com"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("1;[(Reservas,Escrita),(Entregas,Nenhuma),(Usuarios,Escrita)] 2;[(Reservas,Escrita),(Entregas,Leitura),(Usuarios,Escrita)]"));
    }

}
