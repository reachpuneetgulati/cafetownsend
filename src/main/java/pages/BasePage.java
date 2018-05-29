package pages;

import common.Browser;

public class BasePage {

    public boolean IsAt(String name){
        return Browser.getInstance().getTitle().equals(name);
    }

    public String escapeForXpath(String variable) {
        if (variable.contains("'") || variable.contains("\"")) {
            return "concat('" + variable.replace("'", "', \"'\", '") + "', '')";
        }
        return "'" + variable + "'";
    }

    public String prepareXpath(String template, Object...params){
        for (int i = 0; i < params.length; ++i) {
            if (params[i] instanceof String) {
                params[i] = escapeForXpath((String)params[i]);
            }
        }
        return String.format(template, params);
    }

    public void pauseExecution(){
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
