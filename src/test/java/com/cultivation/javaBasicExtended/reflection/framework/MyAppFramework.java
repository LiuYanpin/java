package com.cultivation.javaBasicExtended.reflection.framework;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("WeakerAccess")
public class MyAppFramework {
    private Set<Class> controllerSet = new HashSet<>();

    public void registerController(Class controllerClazz) {
        if (controllerSet.contains(controllerClazz)) {
            throw new IllegalArgumentException("IllegalArgumentException");
        }
        controllerSet.add(controllerClazz);
    }

    public Response getResponse(String requestController, String requestMethod) {
        Response notFoundResponse = new Response(404);
        Response successResponse;
        Class requestControllerClass = getController(requestController);
        if (requestControllerClass == null) {
            return notFoundResponse;
        }

        int methodStatus = getMethodStatus(requestControllerClass, requestMethod);
        if (methodStatus != 200) {
            return new Response(methodStatus);
        }

        Method method = getMethod(requestControllerClass, requestMethod);
        if (method == null) {
            return notFoundResponse;
        }

        try {
            successResponse = (Response)method.invoke(requestControllerClass.newInstance());
        }catch (Exception e){
            if (e.getCause() != null) {

            }
            return new Response(e.getCause().getMessage(),500);
        }
        return successResponse;
    }

    private Class getController(String toBeTestRequestController) {
        for (Class controllerClass: controllerSet) {
            if (controllerClass.getName().equals(toBeTestRequestController)) {
                return controllerClass;
            }
        }
        return null;
    }

    private Method getMethod(Class controllerClass, String requestMethod) {
        try {
            return controllerClass.getMethod(requestMethod);
        }catch (Exception e) {
            return null;
        }
    }

    private int getMethodStatus(Class requestControllerClass, String requestMethod) {
        Method[] methods = requestControllerClass.getDeclaredMethods();
        for (Method method: methods) {
            if (method.getName() == requestMethod) {
                if (method.getModifiers() != 1) {
                    return 403;
                }
                if (method.getParameterCount() > 0 || method.getReturnType() != Response.class) {
                    return 503;
                }
            }

        }
        return 200;
    }
}