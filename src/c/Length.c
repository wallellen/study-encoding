#include <stdio.h>
#include <wchar.h>
#include <locale.h>

static int _strlen(const char* s) {
    const char* p = s;
    while (*s++);
    return s - p - 1;
}

static int _wcslen(const wchar_t* s) {
    const wchar_t* p = s;
    while (*s++);
    return s - p - 1;
}

static int _mbslen(const char* s) {
    int len = 0;
    while (*s) {
        unsigned char c = *s, seed = 0x80;
        int count = 1;
        while ((c & seed) > 0) {
            count++;
            seed >>= 1;
        }
        s += count;
        len++;
    }
    return len;
}

int main(int argc, char* argv[]) {
    const char ascii_s[] = "hello world";
    const char unicode_s[] = "真牛B啊";
    const wchar_t ucs_s[] = L"真牛B啊";

    setlocale(LC_ALL, "");
    printf("length of %s is %d, and byte size is %ld\n", ascii_s, _strlen(ascii_s), sizeof(ascii_s));
    printf("length of %s is %d, and byte size is %ld\n", unicode_s, _strlen(unicode_s), sizeof(unicode_s));
    printf("length of %s is %d, and byte size is %ld\n", unicode_s, _mbslen(unicode_s), sizeof(unicode_s));
    wprintf(L"length of %ls is %d, and byte size is %ld\n", ucs_s, _wcslen(ucs_s), sizeof(ucs_s));
    return 0;
}