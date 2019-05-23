import React from 'react';
import { View, Text, StyleSheet } from 'react-native';

const LineCardapio = (props) => {
    const { title, content } = props;

    return (
        <View style={styles.line}>
            <Text style={styles.title}>{title}</Text>
            <Text style={styles.content}>{content}</Text>
        </View>
    );
};

const styles = StyleSheet.create({
    line: {
        flexDirection: 'row',
        justifyContent: 'space-between',
    },

    title: {
        alignSelf: 'flex-start',
        padding: 10
    },

    content: {
        alignSelf: 'flex-end',
        color: '#a9a9a9',
        padding: 10
    }
});

export default LineCardapio;