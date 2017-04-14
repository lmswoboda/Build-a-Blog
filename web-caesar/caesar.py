def alphabet_position(char):
    import string
    if char in string.ascii_lowercase:
        char_value = string.ascii_lowercase.index(char)
    if char in string.ascii_uppercase:
        char_value = string.ascii_uppercase.index(char)
    return char_value

def rotate_character(char, rot):
    import string
    char_value = alphabet_position(char)
    rotated_char_value = int(char_value) + int(rot)
    if rotated_char_value > 25:
        rotated_char_value = rotated_char_value - 26
    if char in string.ascii_lowercase:
        rotated_char = string.ascii_lowercase[rotated_char_value]
    if char in string.ascii_uppercase:
        rotated_char = string.ascii_uppercase[rotated_char_value]
    return rotated_char

def encrypt(text, rot):
    import string
    rotated_text = ''
    for char in text:
        if char in string.ascii_lowercase or char in string.ascii_uppercase:
            rotated_text = rotated_text + rotate_character(char,rot)

        else:
            rotated_text = rotated_text + char

    return rotated_text
