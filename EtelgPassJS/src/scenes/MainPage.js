import React from 'react';
import { ActivityIndicator } from 'react-native';
import { createMaterialTopTabNavigator } from 'react-navigation';
import Cardapio from './Cardapio';
import Carteirinha from './Carteirinha';
import Notas from './Notas';
import Icon from 'react-native-vector-icons/MaterialIcons';


const Navigation = createMaterialTopTabNavigator({
    Carteirinha: {
      screen: Carteirinha,
      navigationOptions: {
        tabBarLabel: 'Carteirinha',
        tabBarIcon: ({ tintColor }) => (
          <Icon name="account-circle" color={tintColor} size={26} />
          )
      }
    },
    
    Notas: {
      screen: Notas,
      navigationOptions: {
        tabBarLabel: 'Notas',
        tabBarIcon: ({ tintColor }) => (
          <Icon name="create" color={tintColor} size={26} />
          )
      }
    },

    Cardapio: {
      screen: Cardapio,
      navigationOptions: {
        tabBarLabel: 'Cardapio',
        tabBarIcon: ({ tintColor }) => (
          <Icon name="local-dining" color={tintColor} size={26} />
          )
      }
    }
  }, {
    initialRouteName: 'Carteirinha',
    tabBarPosition: 'bottom',
    swipeEnabled: true,
    tabBarOptions: {
      activeTintColor: '#00C667',
      inactiveTintColor: 'gray',
      animationEnabled: true,
      style: {
        backgroundColor: 'white',
        borderTopWidth: 0.5,
        borderTopColor: 'grey'
      },
      showIcon: true,
      indicatorStyle: {
        height: 0
      } 
  }
  });

export default class MainPage extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      result: [],
      loading: false
    };
  }

  componentWillMount() {
    this.setState({loading: true});
    const { navigation } = this.props;
    const resultado = navigation.getParam('resultado', 'null');
    const rm = navigation.getParam('RM', 'null');

    this.setState({
      result: resultado,
      RM: rm
    });
  }

  componentDidMount() {
    this.setState({loading: false});
  }

  render() {
    if(this.state.loading) {
      return <ActivityIndicator />
    }
    
    return <Navigation screenProps={{ dados: this.state.result, rm: this.state.RM }} />
  }
} 