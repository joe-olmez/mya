package com.olmez.mya.utility;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class StringUtilityTest {

    @Test
    void testBasic() {
        String str = "toronto.docx";
        String[] split = str.split("\\.");
        assertThat(split[0]).isEqualTo("toronto");
        assertThat(split[1]).isEqualTo("docx");
    }

    @Test
    void testGenLong() {
        Long num = IdGen.genLong();
        assertThat(num).isNotNull();
    }

}
