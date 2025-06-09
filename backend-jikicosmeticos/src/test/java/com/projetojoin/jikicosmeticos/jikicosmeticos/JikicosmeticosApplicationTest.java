package com.projetojoin.jikicosmeticos.jikicosmeticos;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import com.projetojoin.jikicosmeticos.jikicosmeticos.config.TestMailConfig;
import org.springframework.context.annotation.Import;

@SpringBootTest
@Import(TestMailConfig.class)
class JikicosmeticosApplicationTests {

    @Test
    void contextLoads() {
    }

}