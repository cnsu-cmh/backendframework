package com.xiaoshu.backendframework;

import com.xiaoshu.backendframework.pkgscanner.PkgScanner;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.Table;
import java.io.IOException;
import java.util.List;

public class ScanTest {

    @Test
    public void testScanWithName() throws IOException {
        PkgScanner scanner = new PkgScanner("com.xiaoshu.backendframework.model");
        List<String> list = scanner.scan();

        Assert.assertTrue(list.indexOf("com.xiaoshu.backendframework.model.BaseEntity") != -1);

        list.forEach(l -> System.out.println(l));
    }

    @Test
    public void testScanWithAnnotation() throws IOException {
        PkgScanner scanner = new PkgScanner("com.xiaoshu.backendframework.model", Table.class);
        List<String> list = scanner.scan();

       // Assert.assertTrue(list.indexOf("com.xiaoshu.backendframework.model.SysUser") != -1);

        list.forEach(l -> System.out.println(l));
    }
}
