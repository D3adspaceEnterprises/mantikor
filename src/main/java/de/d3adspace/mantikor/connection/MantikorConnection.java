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

package de.d3adspace.mantikor.connection;

import de.d3adspace.mantikor.MantikorServer;
import de.d3adspace.mantikor.http.HTTPRequest;
import de.d3adspace.mantikor.http.HTTPResponse;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * Representing a connection to a client.
 *
 * @author Felix 'SasukeKawaii' Klauke
 */
public class MantikorConnection extends SimpleChannelInboundHandler<HTTPRequest> {
	
	/**
	 * The channel to the client.
	 */
	private final Channel channel;
	
	/**
	 * The server the connection belongs to.
	 */
	private final MantikorServer server;
	
	/**
	 * Create a new connection by the channel to the client and the server it belongs to.
	 *
	 * @param channel The channel.
	 * @param server The server.
	 */
	public MantikorConnection(Channel channel, MantikorServer server) {
		this.channel = channel;
		this.server = server;
	}
	
	@Override
	protected void channelRead0(ChannelHandlerContext channelHandlerContext, HTTPRequest request)
		throws Exception {
		
		HTTPResponse response = this.server.handleRequest(request);
		response.writeDefaultHeader();
		
		this.channel.writeAndFlush(response);
	}
}