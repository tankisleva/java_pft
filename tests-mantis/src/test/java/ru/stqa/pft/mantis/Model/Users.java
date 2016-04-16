package ru.stqa.pft.mantis.Model;

import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by oleg on 16.04.16.
 */
public class Users extends ForwardingSet<UserData> {

    private Set<UserData> delegate;

    public Users(Collection<UserData> users) {
        this.delegate =  new HashSet<UserData>(users);
    }

    @Override
    protected Set<UserData> delegate() {
        return delegate;
    }


}
