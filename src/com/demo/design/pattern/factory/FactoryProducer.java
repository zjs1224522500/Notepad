package com.demo.design.pattern.factory;

import com.demo.design.pattern.constants.INotepadConstants;
import com.demo.design.pattern.factory.impl.PopMenuItemFactory;
import com.demo.design.pattern.factory.impl.StaticMenuItemFactory;

/**
 * 工厂模式
 */
public class FactoryProducer {

    private static AbstractMenuItemFactory staticMenuItemFactory = null;

    private static AbstractMenuItemFactory popMenuItemFactory = null;

    public static AbstractMenuItemFactory getFactory(String type) {
        /**
         * 单例模式
         */
        if (INotepadConstants.STATIC_MENU_ITEM_TYPE.equals(type)) {
            if (null == staticMenuItemFactory) {
                staticMenuItemFactory = new StaticMenuItemFactory();
            }
            return staticMenuItemFactory;

        } else if (INotepadConstants.POP_MENU_ITEM_TYPE.equals(type)) {
            if (null == popMenuItemFactory) {
                popMenuItemFactory = new PopMenuItemFactory();
            }
            return popMenuItemFactory;

        }
        return null;
    }
}
