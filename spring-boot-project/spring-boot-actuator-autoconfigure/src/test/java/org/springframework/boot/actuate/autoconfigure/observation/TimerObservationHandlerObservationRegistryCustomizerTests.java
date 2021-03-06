/*
 * Copyright 2012-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.actuate.autoconfigure.observation;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.ObjectProvider;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tests for {@link TimerObservationHandlerObservationRegistryCustomizer}.
 *
 * @author Moritz Halbritter
 */
class TimerObservationHandlerObservationRegistryCustomizerTests {

	@Test
	@SuppressWarnings("unchecked")
	void customizeInstallsTimerObservationHandler() {
		MeterRegistry meterRegistry = new SimpleMeterRegistry();
		ObjectProvider<MeterRegistry> meterRegistryProvider = Mockito.mock(ObjectProvider.class);
		Mockito.when(meterRegistryProvider.getObject()).thenReturn(meterRegistry);
		TimerObservationHandlerObservationRegistryCustomizer sut = new TimerObservationHandlerObservationRegistryCustomizer(
				meterRegistryProvider);
		ObservationRegistry observationRegistry = ObservationRegistry.create();
		sut.customize(observationRegistry);
		Observation.start("test-1", observationRegistry).stop();
		assertThat(meterRegistry.find("test-1").timer().count()).isEqualTo(1);
	}

}
