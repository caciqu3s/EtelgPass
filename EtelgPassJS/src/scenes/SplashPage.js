import React from 'react';
import { Image, View, StyleSheet, Text, TouchableWithoutFeedback } from 'react-native';
import axios from 'axios';

export default class SplashPage extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            message: "Version 2.0 'Caribbean'",
        };
    }

    componentDidMount() {
        setTimeout(() => {
        this.props.navigation.navigate('Login');
     },3000);
    }
    

    render() {
            return (
                <View style={styles.container}>
                    <TouchableWithoutFeedback onPress={() => {this.setState({message: "Yo soy la revolution"})}}>
                        <Image style={styles.image}
                            source={require('../images/logo.png')}/>
                    </TouchableWithoutFeedback>
                     
                    <Text style={styles.text}>EtelgPass</Text>
                    <Text style={styles.version}>{this.state.message}</Text>
                    
                </View>
            ); 
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        alignItems: 'center',
        justifyContent: 'center',
        backgroundColor: 'white'
    },

    image:{
        aspectRatio: 1,
        height: 200,
        marginLeft: 30
    },

    text: {
        fontSize: 20,

    },

    version: {
        fontSize: 15
    }
});