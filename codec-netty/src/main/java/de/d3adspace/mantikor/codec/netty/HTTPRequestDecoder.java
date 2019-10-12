/*
 * Copyright (c) 2017 D3adspace
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package de.d3adspace.mantikor.codec.netty;

import de.d3adspace.mantikor.commons.HTTPRequest;
import de.d3adspace.mantikor.commons.parser.HTTPRequestMessageFactory;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;

/**
 * A simple decoder for netty to handle HTTP Requests.
 *
 * @author Felix Klauke <info@felix-klauke.de>
 * @author Ruby Hale <ruby@d3adspace.de>
 */
public class HTTPRequestDecoder extends ByteToMessageDecoder {

  private final HTTPRequestMessageFactory requestParser = new HTTPRequestMessageFactory();

  @Override
  protected void decode(ChannelHandlerContext channelHandlerContext,
    ByteBuf byteBuf,
    List<Object> list) {

    // Read raw bytes
    byte[] bytes = new byte[byteBuf.readableBytes()];
    byteBuf.readBytes(bytes);

    // Get raw data
    String rawData = new String(bytes);

    // parse request
    HTTPRequest request = requestParser.parse(rawData);

    list.add(request);
  }
}
