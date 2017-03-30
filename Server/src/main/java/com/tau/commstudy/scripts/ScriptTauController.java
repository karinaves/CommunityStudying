package com.tau.commstudy.scripts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scriptTau")
public class ScriptTauController {

    @Autowired
    private ScriptTauService st;

    @RequestMapping(method = RequestMethod.GET, value = "/run")
    public void dataBase(String path, Long id) throws Exception {
	st.init(path, id);
	st.run();

    }

}
