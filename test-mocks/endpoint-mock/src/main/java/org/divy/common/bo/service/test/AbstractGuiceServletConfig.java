package org.divy.common.bo.service.test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.servlet.GuiceServletContextListener;

public abstract class AbstractGuiceServletConfig extends GuiceServletContextListener{

    @Override
      protected Injector getInjector() {
        return Guice.createInjector(getServletModule());
      }

    protected abstract Module[] getServletModule();

}
