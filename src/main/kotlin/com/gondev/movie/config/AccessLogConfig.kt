package com.gondev.movie.config

import org.apache.catalina.valves.AccessLogValve
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory
import org.springframework.boot.web.server.WebServerFactoryCustomizer
import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Component
import java.io.IOException
import javax.servlet.*
import javax.servlet.http.HttpServletRequest


@Configuration
class AccessLogConfig : WebServerFactoryCustomizer<TomcatServletWebServerFactory?> {
    override fun customize(factory: TomcatServletWebServerFactory?) {
        val accessLogValve = AccessLogValve()
        accessLogValve.pattern = "%{yyyy-MM-dd HH:mm:ss}t\t%s\t%r\t%{User-Agent}i\t%{Referer}i\t%a\t%b"
        accessLogValve.directory = "."
        accessLogValve.suffix = ".log"
        accessLogValve.condition = "ignoreLogging"
        factory?.addContextValves(accessLogValve)
    }
}

@Component
class LoggingFilter : Filter {
    @Throws(IOException::class, ServletException::class)
    override fun doFilter(req: ServletRequest,
                 res: ServletResponse?,
                 chain: FilterChain) {
        val url = (req as HttpServletRequest).requestURI
        if (url.matches(Regex("/(health|.+\\.(ico|js))"))) {
            req.setAttribute("ignoreLogging", true)
        }
        chain.doFilter(req, res)
    }
}