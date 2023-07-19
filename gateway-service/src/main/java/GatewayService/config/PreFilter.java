// package GatewayService.config;

// import com.netflix.zuul.ZuulFilter;
// import com.netflix.zuul.context.RequestContext;
// import javax.servlet.http.HttpServletRequest;

// public class PreFilter extends ZuulFilter {

//     @Override
//     public String filterType() {
//         return "pre"; // Filter type: "pre" indicates a pre-routing filter.
//     }

//     @Override
//     public int filterOrder() {
//         return 1; // Filter order. Lower values are executed first.
//     }

//     @Override
//     public boolean shouldFilter() {
//         return true; // Enable this filter for all requests.
//     }

//     @Override
//     public Object run() {
//         RequestContext ctx = RequestContext.getCurrentContext();
//         // Get the HttpServletRequest from the RequestContext
//         HttpServletRequest request = ctx.getRequest();

//         // Here, you can perform any pre-processing you need on the request.
//         // For example, logging request details or modifying headers.

//         // Log the request method and URL
//         System.out.println("Request Method: " + request.getMethod() + ", URL: " + request.getRequestURL().toString());

//         // Modify headers if needed
//         ctx.addZuulRequestHeader("Custom-Header", "Custom Value");

//         return null; // Return null to indicate that the request should proceed to the next filter in the chain.
//     }
// }
