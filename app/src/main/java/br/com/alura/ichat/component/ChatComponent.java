package br.com.alura.ichat.component;

import br.com.alura.ichat.MainActivity;
import br.com.alura.ichat.module.ChatModule;
import dagger.Component;

/**
 * Created by Millfford on 11/08/2017.
 */

@Component(modules = ChatModule.class)
public interface ChatComponent {

    void inject(MainActivity activity);

}
