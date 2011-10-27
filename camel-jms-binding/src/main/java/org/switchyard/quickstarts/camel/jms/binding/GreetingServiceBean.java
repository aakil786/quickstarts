/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010-2011 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors
 *
 * This copyrighted material is made available to anyone wishing to use,
 * modify, copy, or redistribute it subject to the terms and conditions
 * of the GNU Lesser General Public License, v. 2.1.
 * This program is distributed in the hope that it will be useful, but WITHOUT A
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more
 * details. You should have received a copy of the GNU Lesser General Public
 * License, v.2.1 along with this distribution; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA
 * 02110-1301, USA.
 */

package org.switchyard.quickstarts.camel.jms.binding;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.switchyard.component.bean.Service;

/**
 * A POJO Service implementation.
 *
 * @author Daniel Bevenius
 *
 */
@Service(GreetingService.class)
public class GreetingServiceBean
    implements org.switchyard.quickstarts.camel.jms.binding.GreetingService {
    
     @PersistenceContext (name="CamelJmsBindingUnit")
     private EntityManager em;

    @Override
    public final void greet(final String name) {
        final GreeterEntity greeter = em.find(GreeterEntity.class, name);
        
        if (greeter == null) {
            System.out.println("Hello " + name);
            em.persist(new GreeterEntity(name));
//            throw new IllegalArgumentException("Trying to trigger a rollback and redelivery");
        }
        else {
            System.out.println("We have already greeted you " + name);
        }
    }
        
}
