package br.com.criptosys.util.dto;

import br.com.criptosys.util.PojoTestUtil;
import org.junit.jupiter.api.Test;

public class GettersAndSettersTest {

    @Test
    public void testDtoPackage() {
        PojoTestUtil.validatePackage("br.com.criptosys.dto");
    }

    @Test
    public void testDomainPackage() {
        PojoTestUtil.validatePackage("br.com.criptosys.domain.entity");
    }
}
