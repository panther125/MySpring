package com.panther.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Gin 琴酒
 * @data 2023/10/24 17:23
 */
public interface Resource {

    InputStream getInputStream() throws IOException;

}
