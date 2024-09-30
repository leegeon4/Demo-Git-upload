package org.iclass.board;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppConfig implements WebMvcConfigurer {
    
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // c:/upload 경로를 url 로 매핑하기 위함.
        // 개발자가 url을 정해줍니다.
        final String URL_PATH = "/upload/**";
        // 파일시스템 위치
        final String LOCATION = "file:///c:/upload/";
        
        // 위의 2개 값을 매핑
        registry.addResourceHandler(URL_PATH)
                .addResourceLocations(LOCATION);
    }

    // 설정이 완료되면 c:\\upload 폴더의 파일은 브라우저에 url로 접근 가능.
    //  http://localhost:8080/upload/image.png
}
