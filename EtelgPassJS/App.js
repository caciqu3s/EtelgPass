import { createStackNavigator } from 'react-navigation';
import LoginPage from './src/scenes/LoginPage';
import MainPage from './src/scenes/MainPage';
import SplashPage from './src/scenes/SplashPage';

export default createStackNavigator({
    'Splash': {
        screen: SplashPage
    },
    
    'Login': {
        screen: LoginPage
    },
    
    'Principal': {
        screen: MainPage
    },

}, {
    navigationOptions: {
        header: null
    }
})