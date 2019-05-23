import React from 'react';
import { View, Text, StyleSheet } from 'react-native';

const LineFaltas = (props) => {
    const { content } = props;

    return (
        <View style={[styles.line]}>
            <Text style={[styles.text]}>{content}</Text>
        </View>
    );
};

const styles = StyleSheet.create({
    line: {
        flexDirection: 'row',
        paddingTop: 10,
        paddingBottom: 3,
        paddingLeft: 10,
        paddingRight: 10,
        justifyContent: 'center'
    },

    text: {
        fontSize: 15
    }
});

export default LineFaltas;