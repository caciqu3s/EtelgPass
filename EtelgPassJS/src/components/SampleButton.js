import React from 'react';
import { View, Text, StyleSheet, TouchableNativeFeedback } from 'react-native';

const SampleButton = (props) => {
    const {title} = props;
    const {Click} = props;

    return (
        <TouchableNativeFeedback onPress={() => {Click()}}>
            <View style={styles.button}>
                <Text style={styles.text}>{title}</Text>
            </View>
        </TouchableNativeFeedback>
        
    );
};

const styles = StyleSheet.create({
    button: {
        borderRadius: 20,
        width: 330,
        height: 40,
        backgroundColor: "#00c667",
        alignItems: 'center',
        justifyContent: 'center',
        elevation: 2,
        alignSelf: 'center'
    },

    text: {
        color: "#FFF",
        fontSize: 16,
        fontWeight: 'bold'
    }
});

export default SampleButton;