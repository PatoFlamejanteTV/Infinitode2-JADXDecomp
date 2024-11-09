package com.badlogic.gdx.utils;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonValue;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

/* loaded from: infinitode-2.jar:com/badlogic/gdx/utils/UBJsonReader.class */
public class UBJsonReader implements BaseJsonReader {
    public boolean oldFormat = true;

    @Override // com.badlogic.gdx.utils.BaseJsonReader
    public JsonValue parse(InputStream inputStream) {
        DataInputStream dataInputStream = null;
        try {
            try {
                dataInputStream = new DataInputStream(inputStream);
                JsonValue parse = parse(dataInputStream);
                StreamUtils.closeQuietly(dataInputStream);
                return parse;
            } catch (IOException e) {
                throw new SerializationException(e);
            }
        } catch (Throwable th) {
            StreamUtils.closeQuietly(dataInputStream);
            throw th;
        }
    }

    @Override // com.badlogic.gdx.utils.BaseJsonReader
    public JsonValue parse(FileHandle fileHandle) {
        try {
            return parse(fileHandle.read(8192));
        } catch (Exception e) {
            throw new SerializationException("Error parsing file: " + fileHandle, e);
        }
    }

    public JsonValue parse(DataInputStream dataInputStream) {
        try {
            return parse(dataInputStream, dataInputStream.readByte());
        } finally {
            StreamUtils.closeQuietly(dataInputStream);
        }
    }

    protected JsonValue parse(DataInputStream dataInputStream, byte b2) {
        if (b2 == 91) {
            return parseArray(dataInputStream);
        }
        if (b2 == 123) {
            return parseObject(dataInputStream);
        }
        if (b2 == 90) {
            return new JsonValue(JsonValue.ValueType.nullValue);
        }
        if (b2 == 84) {
            return new JsonValue(true);
        }
        if (b2 == 70) {
            return new JsonValue(false);
        }
        if (b2 == 66) {
            return new JsonValue(readUChar(dataInputStream));
        }
        if (b2 == 85) {
            return new JsonValue(readUChar(dataInputStream));
        }
        if (b2 == 105) {
            return new JsonValue(this.oldFormat ? dataInputStream.readShort() : dataInputStream.readByte());
        }
        if (b2 == 73) {
            return new JsonValue(this.oldFormat ? dataInputStream.readInt() : dataInputStream.readShort());
        }
        if (b2 == 108) {
            return new JsonValue(dataInputStream.readInt());
        }
        if (b2 == 76) {
            return new JsonValue(dataInputStream.readLong());
        }
        if (b2 == 100) {
            return new JsonValue(dataInputStream.readFloat());
        }
        if (b2 == 68) {
            return new JsonValue(dataInputStream.readDouble());
        }
        if (b2 == 115 || b2 == 83) {
            return new JsonValue(parseString(dataInputStream, b2));
        }
        if (b2 == 97 || b2 == 65) {
            return parseData(dataInputStream, b2);
        }
        if (b2 == 67) {
            return new JsonValue(dataInputStream.readChar());
        }
        throw new GdxRuntimeException("Unrecognized data type");
    }

    protected JsonValue parseArray(DataInputStream dataInputStream) {
        JsonValue jsonValue = new JsonValue(JsonValue.ValueType.array);
        byte readByte = dataInputStream.readByte();
        byte b2 = 0;
        if (readByte == 36) {
            b2 = dataInputStream.readByte();
            readByte = dataInputStream.readByte();
        }
        long j = -1;
        if (readByte == 35) {
            long parseSize = parseSize(dataInputStream, false, -1L);
            j = parseSize;
            if (parseSize < 0) {
                throw new GdxRuntimeException("Unrecognized data type");
            }
            if (j == 0) {
                return jsonValue;
            }
            readByte = b2 == 0 ? dataInputStream.readByte() : b2;
        }
        JsonValue jsonValue2 = null;
        long j2 = 0;
        while (dataInputStream.available() > 0 && readByte != 93) {
            JsonValue parse = parse(dataInputStream, readByte);
            parse.parent = jsonValue;
            if (jsonValue2 != null) {
                parse.prev = jsonValue2;
                jsonValue2.next = parse;
                jsonValue.size++;
            } else {
                jsonValue.child = parse;
                jsonValue.size = 1;
            }
            jsonValue2 = parse;
            if (j > 0) {
                long j3 = j2 + 1;
                j2 = -1;
                if (j3 >= j) {
                    break;
                }
            }
            readByte = b2 == 0 ? dataInputStream.readByte() : b2;
        }
        return jsonValue;
    }

    protected JsonValue parseObject(DataInputStream dataInputStream) {
        JsonValue jsonValue = new JsonValue(JsonValue.ValueType.object);
        byte readByte = dataInputStream.readByte();
        byte b2 = 0;
        if (readByte == 36) {
            b2 = dataInputStream.readByte();
            readByte = dataInputStream.readByte();
        }
        long j = -1;
        if (readByte == 35) {
            long parseSize = parseSize(dataInputStream, false, -1L);
            j = parseSize;
            if (parseSize < 0) {
                throw new GdxRuntimeException("Unrecognized data type");
            }
            if (j == 0) {
                return jsonValue;
            }
            readByte = dataInputStream.readByte();
        }
        JsonValue jsonValue2 = null;
        long j2 = 0;
        while (dataInputStream.available() > 0 && readByte != 125) {
            byte b3 = readByte;
            String parseString = parseString(dataInputStream, true, b3 == true ? (byte) 1 : (byte) 0);
            JsonValue parse = parse(dataInputStream, b2 == 0 ? dataInputStream.readByte() : b2);
            parse.setName(parseString);
            parse.parent = jsonValue;
            if (jsonValue2 != null) {
                parse.prev = jsonValue2;
                jsonValue2.next = parse;
                jsonValue.size++;
            } else {
                jsonValue.child = parse;
                jsonValue.size = 1;
            }
            jsonValue2 = parse;
            if (j > 0) {
                long j3 = j2 + 1;
                j2 = b3 == true ? 1 : 0;
                if (j3 >= j) {
                    break;
                }
            }
            readByte = dataInputStream.readByte();
        }
        return jsonValue;
    }

    protected JsonValue parseData(DataInputStream dataInputStream, byte b2) {
        byte readByte = dataInputStream.readByte();
        long readUInt = b2 == 65 ? readUInt(dataInputStream) : readUChar(dataInputStream);
        JsonValue jsonValue = new JsonValue(JsonValue.ValueType.array);
        JsonValue jsonValue2 = null;
        long j = 0;
        while (true) {
            long j2 = j;
            if (j2 < readUInt) {
                JsonValue parse = parse(dataInputStream, readByte);
                parse.parent = jsonValue;
                if (jsonValue2 != null) {
                    jsonValue2.next = parse;
                    jsonValue.size++;
                } else {
                    jsonValue.child = parse;
                    jsonValue.size = 1;
                }
                jsonValue2 = parse;
                j = j2 + 1;
            } else {
                return jsonValue;
            }
        }
    }

    protected String parseString(DataInputStream dataInputStream, byte b2) {
        return parseString(dataInputStream, false, b2);
    }

    protected String parseString(DataInputStream dataInputStream, boolean z, byte b2) {
        long j = -1;
        if (b2 == 83) {
            j = parseSize(dataInputStream, true, -1L);
        } else if (b2 == 115) {
            j = readUChar(dataInputStream);
        } else if (z) {
            j = parseSize(dataInputStream, b2, false, -1L);
        }
        if (j < 0) {
            throw new GdxRuntimeException("Unrecognized data type, string expected");
        }
        return j > 0 ? readString(dataInputStream, j) : "";
    }

    protected long parseSize(DataInputStream dataInputStream, boolean z, long j) {
        return parseSize(dataInputStream, dataInputStream.readByte(), z, j);
    }

    protected long parseSize(DataInputStream dataInputStream, byte b2, boolean z, long j) {
        if (b2 == 105) {
            return readUChar(dataInputStream);
        }
        if (b2 == 73) {
            return readUShort(dataInputStream);
        }
        if (b2 == 108) {
            return readUInt(dataInputStream);
        }
        if (b2 == 76) {
            return dataInputStream.readLong();
        }
        if (z) {
            return ((b2 & 255) << 24) | ((dataInputStream.readByte() & 255) << 16) | ((dataInputStream.readByte() & 255) << 8) | (dataInputStream.readByte() & 255);
        }
        return j;
    }

    protected short readUChar(DataInputStream dataInputStream) {
        return (short) (dataInputStream.readByte() & 255);
    }

    protected int readUShort(DataInputStream dataInputStream) {
        return dataInputStream.readShort() & 65535;
    }

    protected long readUInt(DataInputStream dataInputStream) {
        return dataInputStream.readInt();
    }

    protected String readString(DataInputStream dataInputStream, long j) {
        byte[] bArr = new byte[(int) j];
        dataInputStream.readFully(bArr);
        return new String(bArr, "UTF-8");
    }
}
