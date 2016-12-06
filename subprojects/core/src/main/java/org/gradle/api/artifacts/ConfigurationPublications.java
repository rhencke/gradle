/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.api.artifacts;

import org.gradle.api.Action;
import org.gradle.api.Incubating;
import org.gradle.api.Named;
import org.gradle.api.NamedDomainObjectContainer;
import org.gradle.api.attributes.AttributeContainer;

import java.util.Map;

/**
 * Represents the outgoing artifacts associated with a configuration. These artifacts are used when the configuration is referenced during dependency resolution.
 *
 * <p>You can use this interface associate artifacts with a configuration using the {@link #artifact(Object)} methods. You can also define several <em>variants</em> of the configuration's artifacts. Each variant represents a set of artifacts that form some mutually exclusive usage of the component.</p>
 *
 * @since 3.3
 */
@Incubating
public interface ConfigurationPublications {
    /**
     * Returns the artifacts associated with this configuration. Any artifact added to this set is also included by all variants. It is also inherited by all configurations that extend  this configuration.
     */
    PublishArtifactSet getArtifacts();

    /**
     * Adds an outgoing artifact to this configuration. This artifact is included in all variants.
     */
    void artifact(Object notation);

    /**
     * Adds an outgoing artifact to this configuration, configuring it using the given action. This artifact is included in all variants.
     */
    void artifact(Object notation, Action<? super ConfigurablePublishArtifact> configureAction);

    /**
     * Returns the variants of this configuration, if any.
     */
    NamedDomainObjectContainer<Variant> getVariants();

    /**
     * Configures the variants of this configuration.
     */
    void variants(Action<? super NamedDomainObjectContainer<Variant>> configureAction);

    /**
     * Represents some variant of the component.
     */
    interface Variant extends Named {
        /**
         * Returns the attributes that define this variant.
         */
        AttributeContainer getAttributes();

        /**
         * Defines some attributes for this variant.
         */
        Variant attributes(Map<String, String> attributes);

        /**
         * Defines an attribute for this variant.
         */
        Variant attribute(String attributeName, String value);

        /**
         * Returns the artifacts associated with this variant.
         */
        PublishArtifactSet getArtifacts();

        /**
         * Adds an artifact to this variant.
         */
        void artifact(Object notation);

        /**
         * Adds an artifact to this variant, configuring it using the given action.
         */
        void artifact(Object notation, Action<? super ConfigurablePublishArtifact> configureAction);
    }
}