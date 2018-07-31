package com.discoid.weather.fiveday.city;

import com.discoid.weather.fiveday.io.IWeatherInteractor;
import com.discoid.weather.fiveday.model.Weather;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.Single;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class CityPresenterTest {

    @Mock
    private IWeatherInteractor weatherInteractor;

    @Mock
    private ICityPresenterView cityView;

    @Captor
    ArgumentCaptor<Integer> cityCodeArgumentCaptor;

    private ICityPresenter unt;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        unt = new CityPresenter(weatherInteractor);
    }

    @Test
    public void checkInstance() {
        assertNotNull(unt);
    }

    @Test
    public void checkStart() {

        Weather weatherobj = new Weather();
        Single<Weather> singleWeather = Single.just(weatherobj);
        Integer cityCode = CityCodes.LONDON;

        given(weatherInteractor.fetch(any(Integer.class))).willReturn(singleWeather);
        unt.start(cityView,cityCode);
        verify(weatherInteractor).fetch(cityCodeArgumentCaptor.capture());
        verify(cityView).display(any(Weather.class));
        assertEquals(cityCode, cityCodeArgumentCaptor.getValue());

    }
}


